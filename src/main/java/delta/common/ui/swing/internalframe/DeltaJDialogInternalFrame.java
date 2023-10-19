package delta.common.ui.swing.internalframe;

import delta.common.ui.swing.Dialog;

/**
 * DeltaJInternalFrame.
 * @author MaxThlon
 */
public class DeltaJDialogInternalFrame extends DeltaJInternalFrame implements Dialog {
  
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
