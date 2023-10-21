package delta.common.ui.swing.windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

import delta.common.ui.swing.Window;
import delta.common.ui.swing.JFrame;
import delta.common.ui.swing.GuiFactory;
import delta.common.ui.swing.icons.ApplicationIcons;

/**
 * Default window controller.
 * @author DAM
 */
public class DefaultWindowController extends AbstractWindowController
{
  /**
   * Constructor.
   */
  public DefaultWindowController()
  {
    super(null);
  }

  /**
   * Constructor.
   * @param parent Parent controller, if any.
   */
  public DefaultWindowController(WindowController parent)
  {
    super(parent);
  }

  /**
   * Get the managed frame.
   * @return the managed frame.
   */
  public JFrame getFrame()
  {
    Window window=getWindow();
    return (window instanceof JFrame)?(JFrame)window:null;
  }

  /**
   * Build the managed window.
   * @return the managed window.
   */
  protected Window buildWindow()
  {
    return build();
  }

  protected JFrame build()
  {
    JFrame frame=GuiFactory.buildFrame();
    List<Image> icons=ApplicationIcons.getApplicationIcons();
    frame.setIconImages(icons);
    _window=frame;
    JMenuBar menuBar=buildMenuBar();
    if (menuBar!=null)
    {
      frame.setJMenuBar(menuBar);
    }
    Container contentPane=frame.getContentPane();
    JComponent component=buildContents();
    if (component!=null)
    {
      contentPane.add(component,BorderLayout.CENTER);
    }
    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    return frame;
  }

  protected JMenuBar buildMenuBar()
  {
    return null;
  }

  /**
   * Show the managed window.
   */
  public void show()
  {
    JFrame frame=getFrame();
    frame.setVisible(true);
  }

  /**
   * Bring the managed window to front.
   */
  public void bringToFront()
  {
    JFrame frame=getFrame();
    frame.setVisible(true);
    frame.setState(java.awt.Frame.NORMAL);
    frame.toFront();
  }

  /**
   * Set window title.
   * @param title Title to set.
   */
  public void setTitle(String title)
  {
    JFrame frame=getFrame();
    frame.setTitle(title);
  }
}
