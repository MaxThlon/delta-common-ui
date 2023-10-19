package delta.common.ui.swing.internalframe;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.apache.log4j.Logger;

/**
 * DeltaJInternalFrame.
 * Dialog come from <a href=https://stackoverflow.com/questions/11177003/making-a-modal-jinternalframe>stackoverflow</a>
 * @author MaxThlon
 */
public class DeltaJInternalFrame extends JInternalFrame implements delta.common.ui.swing.Window {

  /**
   * Glass pane to overlay. Listens for mouse clicks and sets selected
   * on associated modal frame. Also if modal frame has no children make
   * class pane invisible
   */
  class ModalityInternalGlassPane extends JComponent {

    private DeltaJInternalFrame modalFrame;

    public ModalityInternalGlassPane(DeltaJInternalFrame frame) {
        modalFrame = frame;
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (modalFrame.isSelected() == false) {
                    try {
                        modalFrame.setSelected(true);
                        if (modalFrame.hasChildFrame() == false) {
                            setVisible(false);
                        }
                    } catch (PropertyVetoException e1) {
                        //e1.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(255, 255, 255, 100));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
  }

  /**
   * Class DeltaInternalFrameListener.
   * @author MaxThlon
   */
  class DeltaInternalFrameListener implements InternalFrameListener {
    protected WindowListener _windowListener;
    protected Window _window=null;

    public DeltaInternalFrameListener(WindowListener windowListener) {
      _windowListener=windowListener;
    }

    private Window updateWindowAncestor(InternalFrameEvent event) {
      Window window=SwingUtilities.getWindowAncestor((Component)event.getSource());
      if (window != null) _window=window;
      return _window;
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
      _windowListener.windowOpened(new InternalFrameWindowsEvent(e, updateWindowAncestor(e)));
    }
    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
      _windowListener.windowClosing(new InternalFrameWindowsEvent(e, updateWindowAncestor(e)));
    }
    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
      _windowListener.windowClosed(new InternalFrameWindowsEvent(e, updateWindowAncestor(e)));
    }
    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
      _windowListener.windowIconified(new InternalFrameWindowsEvent(e, updateWindowAncestor(e)));
    }
    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
      _windowListener.windowDeiconified(new InternalFrameWindowsEvent(e, updateWindowAncestor(e)));
    }
    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
      _windowListener.windowActivated(new InternalFrameWindowsEvent(e, updateWindowAncestor(e)));
    }
    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
      _windowListener.windowDeactivated(new InternalFrameWindowsEvent(e, updateWindowAncestor(e)));
    }
  }

  /**
   * Class InternalFrameWindowsEvent.
   * @author MaxThlon
   */
  class InternalFrameWindowsEvent extends WindowEvent {
    /**
     * @param event
     * @param window
     */
    public InternalFrameWindowsEvent(InternalFrameEvent event, Window window) {
      super(window, event.getID());
    }

    @Override
    public String paramString() {
        String typeStr;
        switch(id) {
          case InternalFrameEvent.INTERNAL_FRAME_OPENED:
              typeStr = "WINDOW_OPENED";
              break;
          case InternalFrameEvent.INTERNAL_FRAME_CLOSING:
              typeStr = "WINDOW_CLOSING";
              break;
          case InternalFrameEvent.INTERNAL_FRAME_CLOSED:
              typeStr = "WINDOW_CLOSED";
              break;
          case InternalFrameEvent.INTERNAL_FRAME_ICONIFIED:
              typeStr = "WINDOW_ICONIFIED";
              break;
          case InternalFrameEvent.INTERNAL_FRAME_DEICONIFIED:
              typeStr = "WINDOW_DEICONIFIED";
              break;
          case InternalFrameEvent.INTERNAL_FRAME_ACTIVATED:
              typeStr = "WINDOW_ACTIVATED";
              break;
          case InternalFrameEvent.INTERNAL_FRAME_DEACTIVATED:
              typeStr = "WINDOW_DEACTIVATED";
              break;
          default:
              typeStr = "unknown type";
        }
        return typeStr;
    }

    /**
     * @return Window {@code Window}
     */
    public Window getWindow() {
      return (source instanceof Window)? (Window)source : null;
    }
  }

  private static final Logger LOGGER=Logger.getLogger(DeltaJInternalFrame.class);

  protected DeltaJInternalFrame _parent;
  protected DeltaJInternalFrame childFrame;
  protected JComponent focusOwner;
  private boolean wasCloseable;

  /**
   * Constructor
   */
  public DeltaJInternalFrame() {
    this(null);
  }

  /**
   * Constructor
   * @param parent
   */
  public DeltaJInternalFrame(DeltaJInternalFrame parent) {
    super();
    _parent=parent;
    if (parent != null) {
      parent.setChildFrame(this);
    }
    setFocusTraversalKeysEnabled(false);
    // Add glass pane
    ModalityInternalGlassPane glassPane = new ModalityInternalGlassPane(this);
    setGlassPane(glassPane);
    // Add frame veto listener
    addFrameVetoListener();
  }

  @Override
  public void setIconImages(List<? extends Image> icons) {
    if (icons.size() > 0)
      super.setFrameIcon(new ImageIcon(icons.get(0)));
  }

  /*
   * code from {@link Window.setLocationRelativeTo(Component c) setLocationRelativeTo}
   */
  @Override
  public void setLocationRelativeTo(Component componentWindow) {
    // target location
    int dx = 0, dy = 0;
    
    GraphicsConfiguration gc = getGraphicsConfiguration();
    Rectangle gcBounds = gc.getBounds();

    Dimension windowSize = getSize();

    gc = componentWindow.getGraphicsConfiguration();
    gcBounds = gc.getBounds();
    Dimension compSize = componentWindow.getSize();
    Point compLocation = componentWindow.getLocationOnScreen();
    dx = compLocation.x + ((compSize.width - windowSize.width) / 2);
    dy = compLocation.y + ((compSize.height - windowSize.height) / 2);

    // Adjust for bottom edge being offscreen
    if (dy + windowSize.height > gcBounds.y + gcBounds.height) {
        dy = gcBounds.y + gcBounds.height - windowSize.height;
        if (compLocation.x - gcBounds.x + compSize.width / 2 < gcBounds.width / 2) {
            dx = compLocation.x + compSize.width;
        } else {
            dx = compLocation.x - windowSize.width;
        }
    }
    
    // Avoid being placed off the edge of the screen:
    // bottom
    if (dy + windowSize.height > gcBounds.y + gcBounds.height) {
        dy = gcBounds.y + gcBounds.height - windowSize.height;
    }
    // top
    if (dy < gcBounds.y) {
        dy = gcBounds.y;
    }
    // right
    if (dx + windowSize.width > gcBounds.x + gcBounds.width) {
        dx = gcBounds.x + gcBounds.width - windowSize.width;
    }
    // left
    if (dx < gcBounds.x) {
        dx = gcBounds.x;
    }

    setLocation(dx, dy);
  }

  /**
   * Method to handle child opening and becoming visible.
   */
  public void childOpening() {
    // record the present focused component
    wasCloseable=isClosable();
    setClosable(false);
    focusOwner = (JComponent)getFocusOwner();
    grabFocus();
    getGlassPane().setVisible(true);
  }

  /**
   * @return JComponent
   */
  public JComponent getParentFrame() {
    return _parent;
  }

  /**
   * @param childFrame
   */
  public void setChildFrame(DeltaJInternalFrame childFrame) {
    this.childFrame = childFrame;
  }

  /**
   * @return DeltaJDialogInternalFrame
   */
  public DeltaJInternalFrame getChildFrame() {
    return childFrame;
  }

  /**
   * @return boolean
   */
  public boolean hasChildFrame() {
    return (childFrame != null);
  }

  protected void addFrameVetoListener() {
    addVetoableChangeListener(new VetoableChangeListener() {

        public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
            if (evt.getPropertyName().equals(JInternalFrame.IS_SELECTED_PROPERTY)
                    && evt.getNewValue().equals(Boolean.TRUE)) {
                if (hasChildFrame()) {
                    childFrame.setSelected(true);
                    if (childFrame.isIcon()) {
                        childFrame.setIcon(false);
                    }
                    throw new PropertyVetoException("no!", evt);
                }
            }
        }
    });
  }

  /**
   * Method to handle child frame closing and make this frame
   * available for user input again with no glasspane visible
   */
  protected void childClosing() {
    setClosable(wasCloseable);
    getGlassPane().setVisible(false);
    if (focusOwner != null) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    moveToFront();
                    setSelected(true);
                    focusOwner.grabFocus();
                } catch (PropertyVetoException ex) {
                  LOGGER.error(ex);
                }
            }
        });
        focusOwner.grabFocus();
    }
    setChildFrame(null);
  }

  @Override
  public void show() {
     /**
      * Need to inform parent its about to lose its focus due
      * to child opening
      */
    if (_parent != null) _parent.childOpening();
    super.show();
  }

  @Override
  public void setLocationRelativeTo(delta.common.ui.swing.Component c) {
    Component component=null;
    if (c instanceof Component) component=(Component)c;
    setLocationRelativeTo(component);
  }

  @Override
  public void addWindowListener(WindowListener windowListener) {
    super.addInternalFrameListener(new DeltaInternalFrameListener(windowListener));
  }

  @Override
  public void removeWindowListener(WindowListener windowListener) {
    Optional<DeltaInternalFrameListener> deltaInternalFrameListener=
        Arrays.stream(listenerList.getListeners(DeltaInternalFrameListener.class))
          .filter(internalFrameListener -> internalFrameListener._windowListener == windowListener)
          .findFirst();
    
    if(deltaInternalFrameListener.isPresent()) {
      super.removeInternalFrameListener(deltaInternalFrameListener.get());
    }
  }

  @Override
  public void dispose() {
    if (_parent != null) _parent.childClosing();
    super.dispose();
  }
}
