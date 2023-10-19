package delta.common.ui.swing.pattern.factory;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.Paint;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import delta.common.ui.swing.Dialog;
import delta.common.ui.swing.GuiFactory;
import delta.common.ui.swing.windows.DeltaJDialog;
import delta.common.ui.swing.windows.DeltaJFrame;

/**
 * DefaultGuiPatternFactory.
 * @author MaxThlon
 */
public class DefaultGuiPatternFactory implements GuiPatternFactory {
  @Override
  public Paint getBackgroundPaint()
  {
    return UIManager.getColor("background");
  }

  @Override
  public delta.common.ui.swing.Frame buildFrame() {
    delta.common.ui.swing.Frame frame=new DeltaJFrame();
    JPanel backgroundPanel=GuiFactory.buildBackgroundPanel(new BorderLayout());
    frame.setContentPane(backgroundPanel);

    return frame;
  }
  
  @Override
  public Dialog buildDialog(delta.common.ui.swing.Window owner) {
    Dialog dialog=new DeltaJDialog((java.awt.Window)owner);
    dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    return dialog;
  }
  
  @Override
  public JPanel buildBackgroundPanel(LayoutManager layout) {
    return new JPanel(layout);
  }
  
  @Override
  public Border createBevelBorder() {
    return BorderFactory.createBevelBorder(BevelBorder.RAISED);
  }
}
