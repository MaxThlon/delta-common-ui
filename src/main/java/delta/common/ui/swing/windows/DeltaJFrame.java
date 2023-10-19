package delta.common.ui.swing.windows;

import javax.swing.JFrame;

/**
 * DeltaJFrame.
 * @author MaxThlon
 */
public class DeltaJFrame extends JFrame implements delta.common.ui.swing.Frame {

  @Override
  public void setLocationRelativeTo(delta.common.ui.swing.Component c) {
    super.setLocationRelativeTo((c instanceof java.awt.Component)?(java.awt.Component)c:null);
  }
}
