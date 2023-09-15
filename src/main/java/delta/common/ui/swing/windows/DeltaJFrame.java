package delta.common.ui.swing.windows;

import java.awt.Component;

import javax.swing.JFrame;

import delta.common.ui.swing.DeltaComponent;
import delta.common.ui.swing.DeltaFrame;

/**
 * DeltaJFrame.
 * @author MaxThlon
 */
public class DeltaJFrame extends JFrame implements DeltaFrame {

  @Override
  public void setLocationRelativeTo(DeltaComponent c) {
    super.setLocationRelativeTo((c instanceof Component)?(Component)c:null);
  }
}
