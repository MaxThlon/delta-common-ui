package delta.common.ui.swing.pattern.factory;

import java.awt.LayoutManager;
import java.awt.Paint;

import javax.swing.JPanel;
import javax.swing.border.Border;

import delta.common.ui.swing.DeltaDialog;
import delta.common.ui.swing.DeltaFrame;
import delta.common.ui.swing.DeltaWindow;

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
