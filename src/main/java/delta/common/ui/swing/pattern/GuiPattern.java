package delta.common.ui.swing.pattern;

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
import javax.swing.table.JTableHeader;

import delta.common.ui.swing.DeltaFrame;
import delta.common.ui.swing.theme.Theme;

/**
 * Interface for all GuiPatterns.
 * @author MaxThlon
 */
public interface GuiPattern {

  /**
   * initialize with a theme
   * @param theme {@code Theme}
   */
  void initialize(Theme theme);
  /**
   * uninitialize
   */
  void uninitialize();
  /**
   * patternize {@Code GuiPattern} specific properties to {@Code DeltaFrame}
   * @param frame {@Code DeltaFrame}
   */
  void patternize_Frame(DeltaFrame frame);
  
  /**
   * patternize {@Code GuiPattern} specific properties to {@Code JPanel}
   * @param panel {@Code JPanel}
   */
  void patternize_panel(JPanel panel);
  
  /**
   * patternize {@Code GuiPattern} specific properties to {@Code JPanel}
   * @param panel {@Code JPanel}
   */
  void patternize_panel_background(JPanel panel);
  
  /**
   * patternize {@Code GuiPattern} specific properties to {@Code JButton}
   * @param button {@Code JButton}
   */
  void patternize_button_icon(JButton button);
  
  /**
   * patternize {@Code GuiPattern} specific properties to {@Code JLabel}
   * @param label {@Code JLabel}
   */
  void patternize_label(JLabel label);
  
  /**
   * patternize {@Code GuiPattern} specific properties to {@Code JCheckBox}
   * @param checkbox {@Code JCheckBox}
   */
  void patternize_CheckBox(JCheckBox checkbox);
  
  /**
   * patternize {@Code GuiPattern} specific properties to {@Code JTextArea}
   * @param checkbox {@Code JTextArea}
   */
  void patternize_TextArea(JTextArea checkbox);
  
  /**
   * Set {@Code GuiPattern} specific properties to {@Code JList}
   * @param list {@Code JList}
   */
  void patternize_List(JList<?> list);
  
  /**
   * patternize {@Code GuiPattern} specific properties to {@Code JTable} and {@Code JTableHeader}
   * @param table {@Code JTable}
   * @param header {@Code JTableHeader}
   */
  void patternize_Table_and_Header(JTable table, JTableHeader header);
  
  /**
   * patternize {@Code GuiPattern} specific properties to {@Code JScrollPane}
   * @param scrollPane {@Code JScrollPane}
   */
  void patternize_ScrollPane(JScrollPane scrollPane);
  
  /**
   * patternize {@Code GuiPattern} specific properties to {@Code JToolBar}
   * @param toolBar {@Code JToolBar}
   */
  void patternize_ToolBar(JToolBar toolBar);
  
  /**
   * patternize {@Code GuiPattern} specific properties to {@Code JProgressBar}
   * @param progressBar {@Code JProgressBar}
   */
  void patternize_ProgressBar(JProgressBar progressBar);
  
  /**
   * patternize {@Code GuiPattern} specific properties to {@Code JProgressBar}, value {@Code Integer}, maxValue {@Code Integer}
   * @param progressBar {@Code JProgressBar}
   * @param value {@Code Integer}
   * @param maxValue {@Code Integer}
   */
  void patternize_ProgressBar_update(JProgressBar progressBar, Integer value, Integer maxValue);
}
