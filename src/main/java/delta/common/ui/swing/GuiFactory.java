package delta.common.ui.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import delta.common.ui.swing.file.DeltaJFileChooser;
import delta.common.ui.swing.icons.IconsManager;
import delta.common.ui.swing.pattern.GuiPattern;
import delta.common.ui.swing.pattern.GuiPatternConfiguration;
import delta.common.ui.swing.pattern.GuiPatternManager;
import delta.common.ui.swing.pattern.factory.GuiPatternFactory;
import delta.common.utils.misc.Preferences;

/**
 * Factory for GUI items.
 * @author DAM
 */
public abstract class GuiFactory
{
  private static Preferences _preferences;
  private static GuiPatternManager _guiPatternManager;
  private static GuiPatternFactory _guiPatternFactory;
  
  /**
   * Get the UI preferences.
   * @return the UI preferences.
   */
  public static Preferences getPreferences()
  {
    return _preferences;
  }

  /**
   * Set the UI preferences.
   * @param preferences Preferences to use.
   */
  public static void setPreferences(Preferences preferences)
  {
    _preferences=preferences;
  }

  /**
   * Get the gui pattern manager.
   * @return the gui pattern manager.
   */
  public static GuiPatternManager getGuiPatternManager()
  {
    return _guiPatternManager;
  }

  /**
   * Set the Gui pattern manager.
   * @param guiPatternManager
   */
  public static void setGuiPatternManager(GuiPatternManager guiPatternManager)
  {
    _guiPatternManager=guiPatternManager;
  }

  /**
   * Get the Gui pattern manager.
   * @return the Gui pattern manager.
   */
  public static GuiPattern getGuiPattern()
  {
    return _guiPatternManager.getGuiPattern();
  }

  /**
   * getBackgroundColor.
   * @return Color
   */
  public static Color getBackgroundColor()
  {
    return UIManager.getColor("background");
  }
  
  /**
   * getForegroundColor.
   * @return Color
   */
  public static Color getForegroundColor()
  {
    return UIManager.getColor("foreground");
  }
  
  /**
   * getBackgroundPaint.
   * @return Paint
   */
  public static Paint getBackgroundPaint()
  {
    return _guiPatternFactory.getBackgroundPaint();
  }

  /**
   * UI initializations.
   */
  public static void init() {
    if (_guiPatternManager == null) {
      _guiPatternManager = new GuiPatternManager(
          new GuiPatternConfiguration(null)
     );
    }
    _guiPatternManager.initialize();
    if (_guiPatternFactory == null) {
      _guiPatternFactory=_guiPatternManager.newGuiPatternFactory();
    }
    _guiPatternManager.loadSkin();
  }

  /**
   * reloadCache.
   */
  public static void reloadCache() {
    _guiPatternManager.reloadAssets();
  }

  /**
   * Get a new frame.
   * @return a new frame.
   */
  public static Frame buildFrame()
  {
    Frame frame=_guiPatternFactory.buildFrame();
    getGuiPattern().patternize_Frame(frame);
    return frame;
  }

  /**
   * Get a new dialog.
   * @param owner the {@code Window} from which the dialog is displayed or
   *     {@code null} if this dialog has no owner
   * @return a new dialog.
   */
  public static Dialog buildDialog(Window owner)
  {
    Dialog dialog=_guiPatternFactory.buildDialog(owner);
    return dialog;
  }

  /**
   * Get a new fileChooser.
   * @return a new fileChooser.
   */
  public static FileChooser buildFileChooser()
  {
    return new DeltaJFileChooser();
  }
  
  /**
   * Get a new panel using the given layout.
   * @param layout Layout to use.
   * @return a new panel.
   */
  public static JPanel buildPanel(LayoutManager layout)
  {
    JPanel panel=new JPanel(layout);
    getGuiPattern().patternize_panel(panel);
    return panel;
  }

  /**
   * Build a background panel.
   * @param layout Layout manager.
   * @return a new panel.
   */
  public static JPanel buildBackgroundPanel(LayoutManager layout)
  {
    JPanel panel=_guiPatternFactory.buildBackgroundPanel(layout);
    getGuiPattern().patternize_panel_background(panel);
    
    return panel;
  }

  /**
   * Get a new button using the given label.
   * @param label Label to use.
   * @return a new button.
   */
  public static JButton buildButton(String label)
  {
    JButton b=new JButton(label);
    return b;
  }

  /**
   * Get a new button designed to display an unspecified icon.
   * @return a new button.
   */
  public static JButton buildIconButton()
  {
    JButton button=new JButton((Icon)null);
    getGuiPattern().patternize_button_icon(button);
  
    return button;
  }

  /**
   * Get a new button using the given icon.
   * @param iconPath Path of icon to use.
   * @return a new button.
   */
  public static JButton buildIconButton(String iconPath)
  {
    JButton b=buildIconButton();
    ImageIcon icon=IconsManager.getIcon(iconPath);
    if (icon!=null)
    {
      b.setIcon(icon);
      b.setSize(icon.getIconWidth(),icon.getIconHeight());
    }
    return b;
  }

  /**
   * Get a new titled border using the given title.
   * @param title Title to use.
   * @return a new titled border.
   */
  public static TitledBorder buildTitledBorder(String title)
  {
    TitledBorder titledBorder=BorderFactory.createTitledBorder(_guiPatternFactory.createBevelBorder(), title);
    return titledBorder;
  }

  /**
   * Get a new label using the given text.
   * @param label Text to use.
   * @return a new label.
   */
  public static JLabel buildLabel(String label)
  {
    return buildLabel(label,null);
  }

  /**
   * Get a new label using the given text and font size.
   * @param label Text to use.
   * @param size Font size.
   * @return a new label.
   */
  public static JLabel buildLabel(String label, float size)
  {
    return buildLabel(label, Float.valueOf(size));
  }

  /**
   * Build an iconic label.
   * @param icon Icon to display.
   * @return A label.
   */
  public static JLabel buildIconLabel(Icon icon)
  {
    JLabel l=new JLabel(icon);
    l.setOpaque(false);
    return l;
  }

  /**
   * Build a transparent icon label.
   * @param size Size of icon.
   * @return A label.
   */
  public static JLabel buildTransparentIconlabel(int size)
  {
    BufferedImage image=new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
    ImageIcon icon=new ImageIcon(image);
    return buildIconLabel(icon);
  }

  /**
   * Get a new label using the given text.
   * @param label Text to use.
   * @param size Font size.
   * @return a new label.
   */
  private static JLabel buildLabel(String label, Float size)
  {
    JLabel l=new JLabel(label);
    getGuiPattern().patternize_label(l);

    if (size!=null) l.setFont(l.getFont().deriveFont(size.floatValue()));

    return l;
  }

  /**
   * Get a new check box using the given label.
   * @param label Label to use.
   * @return a new checkbox.
   */
  public static JCheckBox buildCheckbox(String label)
  {
    JCheckBox checkbox=new JCheckBox(label);

    getGuiPattern().patternize_CheckBox(checkbox);

    return checkbox;
  }

  /**
   * Get a new text field using the given text.
   * @param text Text to use.
   * @return a new text field.
   */
  public static JTextField buildTextField(String text)
  {
    JTextField tf=new JTextField(text);
    return tf;
  }

  /**
   * Get a new text area using the given text.
   * @param text Text to use.
   * @param opaque Indicates if the component to build is opaque or not.
   * @return a new text area.
   */
  public static JTextArea buildTextArea(String text, boolean opaque)
  {
    JTextArea textArea=new JTextArea(text);
    textArea.setOpaque(opaque);
    getGuiPattern().patternize_TextArea(textArea);

    return textArea;
  }

  /**
   * Get a new combo-box.
   * @return a new combo-box.
   */
  public static <T> JComboBox<T> buildComboBox()
  {
    JComboBox<T> cb=new JComboBox<T>();
    return cb;
  }

  /**
   * Get a new list.
   * @return a new list.
   */
  public static <T> JList<T> buildList()
  {
    JList<T> list=new JList<T>();
    getGuiPattern().patternize_List(list);

    return list;
  }

  /**
   * Get a new table.
   * @return a new table.
   */
  public static JTable buildTable()
  {
    JTable table=new JTable();
    JTableHeader header=table.getTableHeader();
    getGuiPattern().patternize_Table_and_Header(table, header);

    return table;
  }

  /**
   * Get a new tree.
   * @return a new tree.
   */
  public static JTree buildTree()
  {
    DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("root");
    JTree tree=new JTree(new DefaultTreeModel(rootNode));

    return tree;
  }

  /**
   * Get a new scroll pane around the given component.
   * @param component Wrapped component.
   * @return a new scroll pane.
   */
  public static JScrollPane buildScrollPane(Component component)
  {
    JScrollPane scrollPane=new JScrollPane(component);
    getGuiPattern().patternize_ScrollPane(scrollPane);

    return scrollPane;
  }

  /**
   * Get a new tabbed pane.
   * @return a new tabbed pane.
   */
  public static JTabbedPane buildTabbedPane()
  {
    return new JTabbedPane();
  }

  /**
   * Get a new menu bar.
   * @return a new menu bar.
   */
  public static JMenuBar buildMenuBar()
  {
    return new JMenuBar();
  }

  /**
   * Get a new menu using the given text.
   * @param label Text to use.
   * @return a new menu.
   */
  public static JMenu buildMenu(String label)
  {
    return new JMenu(label);
  }

  /**
   * Get a new menu item using the given text.
   * @param label Text to use.
   * @return a new text field.
   */
  public static JMenuItem buildMenuItem(String label)
  {
    return new JMenuItem(label);
  }

  /**
   * Get a progress bar.
   * @param orient  the desired orientation of the progress bar
   * @param min  the minimum value of the progress bar
   * @param max  the maximum value of the progress bar
   * @return a new progress bar.
   */
  public static JProgressBar buildProgressBar(int orient, int min, int max) {
    JProgressBar progressBar=new JProgressBar(SwingConstants.HORIZONTAL,0,100);
    getGuiPattern().patternize_ProgressBar(progressBar);
    return progressBar;
  }

  /**
   * Build a HTML panel.
   * @return a HTML panel.
   */
  public static JEditorPane buildHtmlPanel()
  {
    JEditorPane editor=new JEditorPane("text/html","");
    editor.setEditable(false);
    editor.setOpaque(false);
    return editor;
  }

  /**
   * Load and cache {@code BufferedImage} using the given imageAssetIds.
   * @param imageAssetIds
   */
  public static void loadAssetImages(Stream<String> imageAssetIds)
  {
    _guiPatternManager.loadAssetImages(imageAssetIds);
  }

  /**
   * Find or create a {@code BufferedImage} using the given imageAssetId.
   * @param iconAssetId
   * @return a {@code BufferedImage}.
   */
  public static ImageIcon findAssetIcon(String iconAssetId)
  {
    return _guiPatternManager.findAssetIcon(iconAssetId);
  }

  /**
   * Find or create a {@code BufferedImage} using the given imageAssetId.
   * @param imageAssetId
   * @return a {@code BufferedImage}.
   */
  public static BufferedImage findAssetImage(String imageAssetId)
  {
    return _guiPatternManager.findAssetImage(imageAssetId);
  }
  
  /**
   * Show a modal question dialog.
   * @param parent Parent component.
   * @param message Question message.
   * @param title Title of the dialog window.
   * @param optionType Options configuration.
   * @return A result code (see {@link JOptionPane}).
   */
  public static int showQuestionDialog(Component parent, String message, String title, int optionType)
  {
    int ret=JOptionPane.showConfirmDialog(parent,message,title,optionType);
    return ret;
  }
  
  /**
   * Show a modal question dialog.
   * @param parent Parent component.
   * @param message Question message.
   * @param title Title of the dialog window.
   * @param optionType Options configuration.
   * @return A result code (see {@link JOptionPane}).
   */
  public static int showQuestionDialog(delta.common.ui.swing.Component parent, String message, String title, int optionType) {
    return showQuestionDialog((parent instanceof Component)?(Component)parent:null, message, title, optionType);
  }

  /**
   * Show a information dialog.
   * @param parent Parent component.
   * @param message Information message.
   * @param title Title of the dialog window.
   */
  public static void showInformationDialog(Component parent, String message, String title)
  {
    JOptionPane.showMessageDialog(parent,message,title,JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Show a information dialog.
   * @param parent Parent component.
   * @param message Information message.
   * @param title Title of the dialog window.
   */
  public static void showInformationDialog(delta.common.ui.swing.Component parent, String message, String title)
  {
    showInformationDialog((parent instanceof Component)?(Component)parent:null,message,title);
  }

  /**
   * Show an error dialog.
   * @param parent Parent component.
   * @param message Information message.
   * @param title Title of the dialog window.
   */
  public static void showErrorDialog(Component parent, String message, String title)
  {
    JOptionPane.showMessageDialog((parent instanceof Component)?(Component)parent:null,message,title,JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Show an error dialog.
   * @param parent Parent component.
   * @param message Information message.
   * @param title Title of the dialog window.
   */
  public static void showErrorDialog(delta.common.ui.swing.Component parent, String message, String title)
  {
    showErrorDialog((parent instanceof Component)?(Component)parent:null,message,title);
  }
  
  /**
   * Returns the root component for the current component tree.
   *
   * @param c the component
   * @return the first ancestor of c that's a Window or the last Applet ancestor
   */
  public static delta.common.ui.swing.Component getRoot(Component c) {
    Component root=SwingUtilities.getRoot(c);
    return (root instanceof delta.common.ui.swing.Component)?(delta.common.ui.swing.Component)root:null;
  }
  
  /**
   * Returns the first <code>Window </code> ancestor of <code>c</code>, or
   * {@code null} if <code>c</code> is not contained inside a <code>Window</code>.
   *
   * @param c <code>Component</code> to get <code>Window</code> ancestor
   *        of.
   * @return the first <code>Window </code> ancestor of <code>c</code>, or
   *         {@code null} if <code>c</code> is not contained inside a
   *         <code>Window</code>.
   */
  public static delta.common.ui.swing.Window getWindowAncestor(Component c) {
    java.awt.Window window=SwingUtilities.getWindowAncestor(c);
    return (window instanceof delta.common.ui.swing.Window)?(Window)window:null;
  }
}
