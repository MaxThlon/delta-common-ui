package delta.common.ui.swing.pattern.factory;

import java.awt.LayoutManager;
import java.awt.Paint;

import javax.swing.JPanel;
import javax.swing.border.Border;

import delta.common.ui.swing.JDialog;
import delta.common.ui.swing.JFrame;
import delta.common.ui.swing.Window;

/**
 * Interface for all GuiPatternFactories.
 * @author MaxThlon
 */
public interface GuiPatternFactory {
  /**
   * getBackgroundPaint.
   * @return Paint
   */
  Paint getBackgroundPaint();
  
  /**
   * Get a new frame.
   * @return a new frame.
   */
  JFrame buildFrame();

  /**
   * Get a new dialog.
   * @param owner the {@code Window} from which the dialog is displayed or
   *     {@code null} if this dialog has no owner
   * @return a new dialog.
   */
  JDialog buildDialog(Window owner);
  
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
