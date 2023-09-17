package delta.common.ui.swing.pattern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import delta.common.ui.swing.DeltaDialog;
import delta.common.ui.swing.DeltaFrame;
import delta.common.ui.swing.DeltaWindow;
import delta.common.ui.swing.GuiFactory;
import delta.common.ui.swing.dialog.DeltaJDialog;
import delta.common.ui.swing.icons.IconsManager;
import delta.common.ui.swing.pattern.BackGroundGuiPattern.BackGroundGuiFactory;
import delta.common.ui.swing.pattern.GuiPattern.GuiPatternFactory;
import delta.common.ui.swing.windows.DeltaJFrame;

/**
 * BackGroundGuiPattern.
 * @author MaxThlon
 */
public abstract class DefaultGuiPattern implements GuiPattern {
  
  /**
   * BackGroundGuiFactory.
   * @author MaxThlon
   */
  public static class DefaultGuiGuiFactory implements GuiPatternFactory {

    @Override
    public DeltaFrame buildFrame() {
      DeltaFrame frame=new DeltaJFrame();
      JPanel backgroundPanel=GuiFactory.buildBackgroundPanel(new BorderLayout());
      frame.setContentPane(backgroundPanel);

      return frame;
    }
    
    @Override
    public DeltaDialog buildDialog(DeltaWindow owner) {
      return new DeltaJDialog((Window)owner);
    }
    
    @Override
    public JPanel buildBackgroundPanel(LayoutManager layout) {
      return new JPanel(layout);
    }
    
    @Override
    public Border createBevelBorder() {
      return BorderFactory.createBevelBorder(BevelBorder.LOWERED);
    }
  }
  
  @Override
  public Class<? extends GuiPatternFactory> getGuiPatternFactoryClass() {
    return DefaultGuiGuiFactory.class;
  }

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
  public void patternize_Frame(DeltaFrame frame) {}

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
