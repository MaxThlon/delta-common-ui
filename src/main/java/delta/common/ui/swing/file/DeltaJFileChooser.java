package delta.common.ui.swing.file;

import java.awt.Component;

import javax.swing.JFileChooser;

import delta.common.ui.swing.DeltaComponent;
import delta.common.ui.swing.DeltaFileChooser;

/**
 * DeltaJFileChooser.
 * @author MaxThlon
 */
public class DeltaJFileChooser extends JFileChooser implements DeltaFileChooser {

  @Override
  public int showDialog(DeltaComponent parent, String approveButtonText) {
    return super.showDialog((parent instanceof Component)?(Component)parent:null, approveButtonText);
  }
}
