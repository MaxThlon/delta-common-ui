package delta.common.ui.swing.windows;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

/**
 * DeltaJDialog.
 * @author MaxThlon
 */
public class DeltaJDialog extends JDialog implements delta.common.ui.swing.JDialog {
  /**
   * @param owner
   */
  public DeltaJDialog(java.awt.Window owner){
    super(owner);
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
  }

  @Override
  public void setLocationRelativeTo(delta.common.ui.swing.Component c) {
    super.setLocationRelativeTo((c instanceof java.awt.Component)?(java.awt.Component)c:null);
  }
}
