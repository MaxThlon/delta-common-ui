package delta.common.ui.swing.pattern;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import delta.common.ui.swing.JFrame;
import delta.common.ui.swing.theme.Theme;

/**
 * DefaultGuiPattern.
 * @author MaxThlon
 */
public class DefaultGuiPattern implements GuiPattern {
  private static final Logger _logger=Logger.getLogger(DefaultGuiPattern.class);
  
  protected static final Color TRANSPARENT=new Color(0,true);
  
  @Override
  public void initialize(Theme theme) {
    try
    {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException e)
    {
      _logger.warn(e);
    }
    
    Color BACKGROUND=Color.WHITE;
    Color FOREGROUND=Color.BLACK;
    Color FOREGROUND_PROGRESS_BAR=Color.BLUE;
    
    //lookAndFeel.
    UIManager.put("background",BACKGROUND);
    UIManager.put("foreground",FOREGROUND);
    UIManager.put("transparent",TRANSPARENT);

    UIManager.put("OptionPane.background",BACKGROUND);
    UIManager.put("OptionPane.messageForeground", FOREGROUND);

    UIManager.put("Panel.background",BACKGROUND);

    UIManager.put("Button.foreground", FOREGROUND);
    UIManager.put("Button.background", BACKGROUND);
    
    UIManager.put("Label.foreground", FOREGROUND);
    
    UIManager.put("TitledBorder.titleColor", FOREGROUND);
    
    UIManager.put("CheckBox.foreground", FOREGROUND);
    
    UIManager.put("TextField.foreground", FOREGROUND);
    UIManager.put("TextField.background", BACKGROUND);
    
    UIManager.put("TextArea.foreground", FOREGROUND);
    
    UIManager.put("ComboBox.foreground", FOREGROUND);
    UIManager.put("ComboBox.background", BACKGROUND);
    
    
    UIManager.put("TextField.foreground", FOREGROUND);
    UIManager.put("List.background", BACKGROUND);
    
    UIManager.put("TextField.foreground", FOREGROUND);
    UIManager.put("TextField.background", BACKGROUND);
    
    UIManager.put("Table.foreground", FOREGROUND);
    UIManager.put("Table.GridColor", FOREGROUND);
    UIManager.put("TableHeader.foreground", FOREGROUND);
    
    UIManager.put("Table.background", BACKGROUND);
    UIManager.put("TableHeader.background", BACKGROUND);
  
    UIManager.put("ScrollPane.foreground", FOREGROUND);
    UIManager.put("ScrollPane.background", BACKGROUND);

    UIManager.put("Button.foreground", FOREGROUND);
    UIManager.put("Button.background", BACKGROUND);
    UIManager.put("ScrollBar.foreground", FOREGROUND);
    UIManager.put("ScrollBar.background", BACKGROUND);
    
    UIManager.put("TabbedPane.foreground", FOREGROUND);
    UIManager.put("TabbedPane.background", BACKGROUND);
    
    UIManager.put("MenuBar.foreground", FOREGROUND);
    UIManager.put("MenuBar.background", BACKGROUND);
    UIManager.put("Menu.foreground", FOREGROUND);
    UIManager.put("Menu.background", BACKGROUND);
    UIManager.put("MenuItem.foreground", FOREGROUND);
    UIManager.put("MenuItem.background", BACKGROUND);
    
    
    UIManager.put("ToolBar.background", BACKGROUND);

    UIManager.put("ProgressBar.background", BACKGROUND);
    UIManager.put("ProgressBar.foreground", FOREGROUND_PROGRESS_BAR);
  }
  
  @Override
  public void uninitialize() {}
  
  @SuppressWarnings("unused")
  private static void logUIManager() {
    Logger _logger=Logger.getLogger(DefaultGuiPattern.class);
    _logger.setLevel(org.apache.log4j.Level.DEBUG);
    javax.swing.UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    java.util.Set<java.util.Map.Entry<Object, Object>> entries = defaults.entrySet();
    for (java.util.Map.Entry<Object, Object> entry : entries) {
      _logger.debug(entry.getKey() + " = " + entry.getValue());
    }
  }

  @Override
  public void patternize_Frame(JFrame frame) {}

  @Override
  public void patternize_panel(JPanel panel) {
    panel.setOpaque(false);
  }

  @Override
  public void patternize_panel_background(JPanel panel) {}

  @Override
  public void patternize_button_icon(JButton button) {
    button.setBorderPainted(false);
    button.setOpaque(false);
    button.setMargin(new Insets(0,0,0,0));
  }
  
  @Override
  public void patternize_label(JLabel label) {}

  @Override
  public void patternize_CheckBox(JCheckBox checkbox) {}

  @Override
  public void patternize_TextArea(JTextArea checkbox) {}

  @Override
  public void patternize_List(JList<?> list) {}

  @Override
  public void patternize_Table_and_Header(JTable table, JTableHeader header) {}

  @Override
  public void patternize_ScrollPane(JScrollPane scrollPane) {}
  
  @Override
  public void patternize_ToolBar(JToolBar toolBar) {
    toolBar.setFloatable(false);
  }
  
  @Override
  public void patternize_ProgressBar(JProgressBar progressBar) {
    progressBar.setBorderPainted(true);
    progressBar.setStringPainted(true);
  }
  
  private Color getColor(int value, int maxValue) {
    if (value * 100 > maxValue * 80) return Color.RED; // > 80%
    if (value * 100 > maxValue * 50) return Color.YELLOW; // > 80%
    return Color.GREEN;
  }

  @Override
  public void patternize_ProgressBar_update(JProgressBar progressBar, Integer value, Integer maxValue) {
    
    if ((value!=null) && (maxValue!=null))
    {
      Color color=getColor(value.intValue(),maxValue.intValue());
      progressBar.setForeground(color);
      progressBar.setString(value+" / "+maxValue);
      progressBar.setMaximum(maxValue.intValue());
      progressBar.setValue(value.intValue());
    }
    else
    {
      progressBar.setForeground(Color.LIGHT_GRAY);
      progressBar.setString("(unknown)"); // I18n
      progressBar.setMaximum(100);
      progressBar.setValue(100);
    }
  }
}
