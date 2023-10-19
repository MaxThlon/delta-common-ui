package delta.common.ui.swing.file;

import javax.swing.JFileChooser;

import delta.common.ui.swing.FileChooser;

/**
 * DeltaJFileChooser.
 * @author MaxThlon
 */
public class DeltaJFileChooser extends JFileChooser implements FileChooser {

  @Override
  public int showDialog(delta.common.ui.swing.Component parent, String approveButtonText) {
    return super.showDialog((parent instanceof java.awt.Component)?(java.awt.Component)parent:null, approveButtonText);
  }
}
