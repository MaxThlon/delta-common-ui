package delta.common.ui.swing.internalframe;

import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.SecondaryLoop;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
import java.security.AccessController;
import java.security.PrivilegedAction;

import delta.common.ui.swing.JDialog;

/**
 * DeltaJInternalFrame.
 * @author MaxThlon
 */
public class DeltaJDialogInternalFrame extends DeltaJInternalFrame implements JDialog {
  ModalityType _modalityType;
  boolean _modal;
  
  private transient volatile SecondaryLoop _secondaryLoop;
  
  /**
   * Constructor
   * @param parent
   */
  public DeltaJDialogInternalFrame(DeltaJInternalFrame parent) {
    super(parent);
  }

  public ModalityType getModalityType() {
    return _modalityType;
  }
  
  public void setModalityType(ModalityType type) {
    if (type == null) {
        type = Dialog.ModalityType.MODELESS;
    }
    if (!Toolkit.getDefaultToolkit().isModalityTypeSupported(type)) {
        type = Dialog.ModalityType.MODELESS;
    }
    if (_modalityType == type) {
        return;
    }

    _modalityType = type;
    _modal = (_modalityType != ModalityType.MODELESS);
  }
  
  @Override
  public boolean isModal() {
    return _modal;
  }

  @Override
  public void setModal(boolean modal) {
    _modal = modal;
    setModalityType(modal ? Dialog.DEFAULT_MODALITY_TYPE : ModalityType.MODELESS);
  }
  
  @Override
  public void show() {
    super.show();
  
    final EventQueue eventQueue = AccessController.doPrivileged(
        new PrivilegedAction<EventQueue>() {
            public EventQueue run() {
                return Toolkit.getDefaultToolkit().getSystemEventQueue();
            }
    });
    _secondaryLoop = eventQueue.createSecondaryLoop();
    if (!_secondaryLoop.enter()) {
        _secondaryLoop = null;
    }
  }

  @Override
  public void dispose() {
    if (_secondaryLoop != null) {
      _secondaryLoop.exit();
      _secondaryLoop = null;
    }
    super.dispose();
  }
}
