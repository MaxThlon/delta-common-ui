package delta.common.ui.swing.pattern;

import java.awt.LayoutManager;

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
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;

import delta.common.ui.swing.DeltaDialog;
import delta.common.ui.swing.DeltaFrame;
import delta.common.ui.swing.DeltaWindow;

/**
 * Interface for all GuiPatterns.
 * @author MaxThlon
 */
public interface GuiPattern {

  /**
   * Interface for all GuiPatternFactories.
   * @author MaxThlon
   */
  public interface GuiPatternFactory {

    /**
     * Get a new frame.
     * @return a new frame.
     */
    DeltaFrame buildFrame();

    /**
     * Get a new dialog.
     * @param owner the {@code DeltaWindow} from which the dialog is displayed or
     *     {@code null} if this dialog has no owner
     * @return a new dialog.
     */
    DeltaDialog buildDialog(DeltaWindow owner);
    
    /**
     * Build a background panel.
     * @param layout Layout manager.
     * @return a new panel.
     */
    JPanel buildBackgroundPanel(LayoutManager layout);
    
    /**
     * Get a new {@code BevelBorder} using the given title.
     * @return a new {@code BevelBorder}.
     */
    Border createBevelBorder();
  }

  Class<? extends GuiPatternFactory> getGuiPatternFactoryClass();
  
  void initialize();

  /**
   * Set {@Code GuiPattern} specific properties to {@Code DeltaFrame}
   * @param frame {@Code DeltaFrame}
   */
  void patternize_Frame(DeltaFrame frame);
  
  /**
   * Set {@Code GuiPattern} specific properties to {@Code JPanel}
   * @param panel {@Code JPanel}
   */
  void patternize_panel(JPanel panel);
  
  /**
   * Set {@Code GuiPattern} specific properties to {@Code JPanel}
   * @param panel {@Code JPanel}
   */
  void patternize_panel_background(JPanel panel);
  
  /**
   * Set {@Code GuiPattern} specific properties to {@Code JButton}
   * @param button {@Code JButton}
   */
  void patternize_button_icon(JButton button);
  
  /**
   * Set {@Code GuiPattern} specific properties to {@Code JLabel}
   * @param label {@Code JLabel}
   */
  void patternize_label(JLabel label);
  
  /**
   * Set {@Code GuiPattern} specific properties to {@Code JCheckBox}
   * @param checkbox {@Code JCheckBox}
   */
  void patternize_CheckBox(JCheckBox checkbox);
  
  /**
   * Set {@Code GuiPattern} specific properties to {@Code JTextArea}
   * @param checkbox {@Code JTextArea}
   */
  void patternize_TextArea(JTextArea checkbox);
  
  /**
   * Set {@Code GuiPattern} specific properties to {@Code JList}
   * @param list {@Code JList}
   */
  void patternize_List(JList<?> list);
  
  /**
   * Set {@Code GuiPattern} specific properties to {@Code JTable} and {@Code JTableHeader}
   * @param table {@Code JTable}
   * @param header {@Code JTableHeader}
   */
  void patternize_Table_and_Header(JTable table, JTableHeader header);
  
  /**
   * Set {@Code GuiPattern} specific properties to {@Code JScrollPane}
   * @param scrollPane {@Code JScrollPane}
   */
  void patternize_ScrollPane(JScrollPane scrollPane);
  
  /**
   * Set {@Code GuiPattern} specific properties to {@Code JToolBar}
   * @param toolBar {@Code JToolBar}
   */
  void patternize_ToolBar(JToolBar toolBar);
  
  /**
   * Set {@Code GuiPattern} specific properties to {@Code JProgressBar}
   * @param progressBar {@Code JProgressBar}
   */
  void patternize_ProgressBar(JProgressBar progressBar);
  
  /**
   * Set {@Code GuiPattern} specific properties to {@Code JProgressBar}, value {@Code Integer}, maxValue {@Code Integer}
   * @param progressBar {@Code JProgressBar}
   * @param value {@Code Integer}
   * @param maxValue {@Code Integer}
   */
  void patternize_ProgressBar_update(JProgressBar progressBar, Integer value, Integer maxValue);
}
