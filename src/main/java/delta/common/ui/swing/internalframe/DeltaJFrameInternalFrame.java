package delta.common.ui.swing.internalframe;

import delta.common.ui.swing.JFrame;

/**
 * DeltaJInternalFrame.
 * @author MaxThlon
 */
public class DeltaJFrameInternalFrame extends DeltaJInternalFrame implements JFrame {

  @Override
  public void setState(int state)
  {
    // See {@link java.awt.Frame#setState(int state) Sets the state of this frame (obsolete)}
  }
}
