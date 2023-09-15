package delta.common.ui.swing.dialog;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JInternalFrame;

import delta.common.ui.swing.DeltaComponent;
import delta.common.ui.swing.DeltaDialog;

/**
 * DeltaJInternalFrame.
 * @author MaxThlon
 */
public class DeltaJInternalFrame extends JInternalFrame implements DeltaDialog {

  @Override
  public void setIconImages(List<? extends Image> icons) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setModal(boolean modal) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setLocationRelativeTo(Component c) {
    //super.setLocationRelativeTo(c);
  }

  @Override
  public void setLocationRelativeTo(DeltaComponent c) {
    /*Component component=null;
    if (c instanceof Component) component=(Component)c;
    super.setLocationRelativeTo(component);*/
  }

  @Override
  public void addWindowListener(WindowListener arg0) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeWindowListener(WindowListener arg0) {
    // TODO Auto-generated method stub
    
  }
}
