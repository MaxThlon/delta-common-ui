package delta.common.ui.swing.windows;

import delta.common.ui.swing.DeltaFrame;
import delta.common.ui.swing.internalframe.DeltaJInternalFrame;

/**
 * DeltaJInternalFrame.
 * @author MaxThlon
 */
public class DeltaJFrameInternalFrame extends DeltaJInternalFrame implements DeltaFrame {

  @Override
  public void setState(int state)
  {
    // See {@link java.awt.Frame#setState(int state) Sets the state of this frame (obsolete)}
  }
}
