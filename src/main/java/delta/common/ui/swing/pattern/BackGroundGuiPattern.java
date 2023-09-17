package delta.common.ui.swing.pattern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;

import delta.common.ui.swing.BackgroundPanel;
import delta.common.ui.swing.DeltaFrame;
import delta.common.ui.swing.GuiFactory;
import delta.common.ui.swing.icons.IconsManager;

/**
 * BackGroundGuiPattern.
 * @author MaxThlon
 */
public class BackGroundGuiPattern extends DefaultGuiPattern {
  
  /**
   * BackGroundGuiFactory.
   * @author MaxThlon
   */
  public static class BackGroundGuiFactory extends DefaultGuiPattern.DefaultGuiGuiFactory {
    
    @Override
    public JPanel buildBackgroundPanel(LayoutManager layout) {
      return isActiveGuiPattern(USE_BACKGROUND_PATTERN) ?
          new BackgroundPanel(getBackgroundImage(), layout) :
          new JPanel(layout);
    }
    
    @Override
    public Border createBevelBorder() {
      return BorderFactory.createBevelBorder(BevelBorder.LOWERED,FOREGROUND,Color.GRAY);
    }
  }

  private static Color BACKGROUND=Color.WHITE;
  private static Color FOREGROUND=Color.BLACK;
  private static Color TRANSPARENT=new Color(0,true);
  private static final Color FOREGROUND_PROGRESS_BAR=Color.BLUE;
  
  static short USE_DEFAULT_PATTERN = 1;
  static short USE_BACKGROUND_PATTERN = 1<<1;
  private static int _uiPattern=USE_BACKGROUND_PATTERN;

  @Override
  public Class<? extends GuiPatternFactory> getGuiPatternFactoryClass() {
    return BackGroundGuiFactory.class;
  }

  /**
   * @return current uiPattern
   */
  public static int getGuiPattern() {
    return _uiPattern;
  }
  
  /**
   * @param uiPattern
   * @return true if uiPattern is inside current uiPattern
   */
  public static boolean isActiveGuiPattern(int uiPattern) {
    return (_uiPattern&uiPattern) != 0;
  }

  /**
   * Get the standard foreground color.
   * @return a color.
   */
  public static Color getForegroundColor()
  {
    return FOREGROUND;
  }

  /**
   * Get the standard background color.
   * @return a color.
   */
  public static Color getBackgroundColor()
  {
    return BACKGROUND;
  }
  
  /**
   * Get the background pattern image.
   * @return an image.
   */
  public static BufferedImage getBackgroundImage()
  {
    BufferedImage backgroundImage=IconsManager.getImage("/gui/fond.png");
    return backgroundImage;
  }

  /**
   * Get a painter for background.
   * @return A background painter.
   */
  public Paint getBackgroundPaint()
  {
    if (isActiveGuiPattern(USE_BACKGROUND_PATTERN))
    {
      BufferedImage backgroundImage=getBackgroundImage();
      Rectangle r=new Rectangle(0,0,backgroundImage.getWidth(),backgroundImage.getHeight());
      TexturePaint paint=new TexturePaint(backgroundImage,r);
      return paint;
    }
    return BACKGROUND;
  }

  @Override
  public void initialize() {
    //lookAndFeel.
    UIManager.put("OptionPane.background",BACKGROUND);
    //logUIManager();
    UIManager.put("OptionPane.messageForeground", FOREGROUND);

    UIManager.put("Panel.background",BACKGROUND);

    UIManager.put("Button.foreground", FOREGROUND);
    UIManager.put("Button.background", BACKGROUND);
    
    UIManager.put("Label.foreground", FOREGROUND);
    if (isActiveGuiPattern(USE_DEFAULT_PATTERN)){
      UIManager.put("Label.background", BACKGROUND);
    }
    
    UIManager.put("TitledBorder.titleColor", FOREGROUND);
    
    UIManager.put("CheckBox.foreground", FOREGROUND);
    if (isActiveGuiPattern(USE_DEFAULT_PATTERN)){
      UIManager.put("CheckBox.background", BACKGROUND);
    }
    
    UIManager.put("TextField.foreground", FOREGROUND);
    UIManager.put("TextField.background", BACKGROUND);
    
    UIManager.put("TextArea.foreground", FOREGROUND);
    
    UIManager.put("ComboBox.foreground", FOREGROUND);
    UIManager.put("ComboBox.background", BACKGROUND);
    
    
    UIManager.put("TextField.foreground", FOREGROUND);
    if (isActiveGuiPattern(USE_BACKGROUND_PATTERN)) {
      UIManager.put("List.background", TRANSPARENT);
    } else UIManager.put("List.background", BACKGROUND);
    
    UIManager.put("TextField.foreground", FOREGROUND);
    UIManager.put("TextField.background", BACKGROUND);
    
    UIManager.put("Table.foreground", FOREGROUND);
    UIManager.put("Table.GridColor", FOREGROUND);
    UIManager.put("TableHeader.foreground", FOREGROUND);
    if (isActiveGuiPattern(USE_BACKGROUND_PATTERN)) {
      UIManager.put("Table.background", TRANSPARENT);
      UIManager.put("TableHeader.background", TRANSPARENT);
    } else {
      UIManager.put("Table.background", BACKGROUND);
      UIManager.put("TableHeader.background", BACKGROUND);
    }
    
    UIManager.put("ScrollPane.foreground", FOREGROUND);
    if (!isActiveGuiPattern(USE_BACKGROUND_PATTERN))
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
  }

  @Override
  public void patternize_Frame(DeltaFrame frame) {
    frame.getContentPane().setBackground(getBackgroundColor());
  }

  @Override
  public void patternize_panel(JPanel panel) {
    if (isActiveGuiPattern(USE_BACKGROUND_PATTERN)) panel.setOpaque(false);
  }
  
  @Override
  public void patternize_panel_background(JPanel panel) {
    if (isActiveGuiPattern(USE_DEFAULT_PATTERN)) panel.setBackground(BACKGROUND);
  }
  
  @Override
  public void patternize_label(JLabel label) {
    if (isActiveGuiPattern(USE_BACKGROUND_PATTERN)) label.setOpaque(false);
  }

  @Override
  public void patternize_CheckBox(JCheckBox checkbox) {
    if (isActiveGuiPattern(USE_BACKGROUND_PATTERN)) checkbox.setOpaque(false);
  }

  @Override
  public void patternize_TextArea(JTextArea textArea) {
    if (textArea.isOpaque()) textArea.setBackground(BACKGROUND);
  }

  @Override
  public void patternize_List(JList<?> list) {
    if (isActiveGuiPattern(USE_BACKGROUND_PATTERN)) list.setOpaque(false);
  }

  @Override
  public void patternize_Table_and_Header(JTable table, JTableHeader header) {
    if (isActiveGuiPattern(USE_BACKGROUND_PATTERN)) {
      table.setOpaque(false);
      header.setOpaque(false);
    }
  }

  @Override
  public void patternize_ScrollPane(JScrollPane scrollPane) {
    if (isActiveGuiPattern(USE_BACKGROUND_PATTERN))
    {
      scrollPane.setOpaque(false);
      // Make main viewport transparent
      JViewport viewport=scrollPane.getViewport();
      viewport.setOpaque(false);
      viewport.setBackground(TRANSPARENT);
      // Make header transparent (for tables)
      // - create a fake view so that the column header viewport gets created!
      scrollPane.setColumnHeaderView(GuiFactory.buildPanel(new BorderLayout()));
      JViewport hviewport=scrollPane.getColumnHeader();
      hviewport.setOpaque(false);
      hviewport.setBackground(TRANSPARENT);
    }
  }
  
  @Override
  public void patternize_ToolBar(JToolBar toolBar) {
    super.patternize_ToolBar(toolBar);
    toolBar.setBackground(getBackgroundColor());
    toolBar.setOpaque(false);
  }
  
  @Override
  public void patternize_ProgressBar(JProgressBar progressBar) {
    super.patternize_ProgressBar(progressBar);
    progressBar.setBackground(getBackgroundColor());
    progressBar.setForeground(FOREGROUND_PROGRESS_BAR);
  }
}
