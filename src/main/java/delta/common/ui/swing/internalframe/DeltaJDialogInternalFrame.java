package delta.common.ui.swing.internalframe;

import delta.common.ui.swing.JDialog;

/**
 * DeltaJInternalFrame.
 * @author MaxThlon
 */
public class DeltaJDialogInternalFrame extends DeltaJInternalFrame implements JDialog {
  
  /**
   * Constructor
   * @param parent
   */
  public DeltaJDialogInternalFrame(DeltaJInternalFrame parent) {
    super(parent);
  }

  @Override
  public void setModal(boolean modal) {
    // TODO Auto-generated method stub
  }
}
