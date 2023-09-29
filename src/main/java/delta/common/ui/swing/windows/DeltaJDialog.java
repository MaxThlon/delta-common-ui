package delta.common.ui.swing.windows;

import java.awt.Component;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

import delta.common.ui.swing.DeltaComponent;
import delta.common.ui.swing.DeltaDialog;

/**
 * DeltaJDialog.
 * @author MaxThlon
 */
public class DeltaJDialog extends JDialog implements DeltaDialog {
  /**
   * @param owner
   */
  public DeltaJDialog(Window owner){
    super(owner);
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
  }

  @Override
  public void setLocationRelativeTo(DeltaComponent c) {
    super.setLocationRelativeTo((c instanceof Component)?(Component)c:null);
  }
}
