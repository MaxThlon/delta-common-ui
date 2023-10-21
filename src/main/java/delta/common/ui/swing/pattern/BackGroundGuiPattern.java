package delta.common.ui.swing.pattern;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

import delta.common.ui.swing.JFrame;
import delta.common.ui.swing.GuiFactory;
import delta.common.ui.swing.theme.Theme;

/**
 * BackGroundGuiPattern.
 * @author MaxThlon
 */
public class BackGroundGuiPattern extends DefaultGuiPattern {

  @Override
  public void initialize(Theme theme) {
    super.initialize(theme);

    Color BACKGROUND=Color.WHITE;
    Color TRANSPARENT=new Color(0,true);
    
    //lookAndFeel.
    UIManager.put("background.image","/gui/fond.png");
    
    UIManager.put("Label.background", BACKGROUND);
    
    UIManager.put("CheckBox.background", BACKGROUND);
    
    UIManager.put("List.background", TRANSPARENT);
    
    UIManager.put("Table.background", TRANSPARENT);
    UIManager.put("TableHeader.background", TRANSPARENT);
    
    UIManager.put("ScrollPane.background", BACKGROUND);
  }

  @Override
  public void patternize_Frame(JFrame frame) {
    frame.getContentPane().setBackground(UIManager.getColor("background"));
  }

  @Override
  public void patternize_panel(JPanel panel) {
    panel.setOpaque(false);
  }
  
  @Override
  public void patternize_panel_background(JPanel panel) {
    panel.setBackground(UIManager.getColor("background"));
  }
  
  @Override
  public void patternize_label(JLabel label) {
    label.setOpaque(false);
  }

  @Override
  public void patternize_CheckBox(JCheckBox checkbox) {
    checkbox.setOpaque(false);
  }

  @Override
  public void patternize_TextArea(JTextArea textArea) {
    if (textArea.isOpaque()) textArea.setBackground(UIManager.getColor("background"));
  }

  @Override
  public void patternize_List(JList<?> list) {
    list.setOpaque(false);
  }

  @Override
  public void patternize_Table_and_Header(JTable table, JTableHeader header) {
    table.setOpaque(false);
    header.setOpaque(false);
  }

  @Override
  public void patternize_ScrollPane(JScrollPane scrollPane) {
    scrollPane.setOpaque(false);
    // Make main viewport transparent
    JViewport viewport=scrollPane.getViewport();
    viewport.setOpaque(false);
    viewport.setBackground(UIManager.getColor("transparent"));
    // Make header transparent (for tables)
    // - create a fake view so that the column header viewport gets created!
    scrollPane.setColumnHeaderView(GuiFactory.buildPanel(new BorderLayout()));
    JViewport hviewport=scrollPane.getColumnHeader();
    hviewport.setOpaque(false);
    hviewport.setBackground(UIManager.getColor("transparent"));
  }
  
  @Override
  public void patternize_ToolBar(JToolBar toolBar) {
    super.patternize_ToolBar(toolBar);
    toolBar.setOpaque(false);
  }
}
