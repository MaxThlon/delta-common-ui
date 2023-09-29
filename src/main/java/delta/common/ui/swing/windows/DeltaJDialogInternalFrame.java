package delta.common.ui.swing.windows;

import delta.common.ui.swing.DeltaDialog;
import delta.common.ui.swing.internalframe.DeltaJInternalFrame;

/**
 * DeltaJInternalFrame.
 * @author MaxThlon
 */
public class DeltaJDialogInternalFrame extends DeltaJInternalFrame implements DeltaDialog {
  
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
