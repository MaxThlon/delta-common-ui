package delta.common.ui.swing;

import java.awt.Image;
import java.awt.event.WindowListener;

/**
 * @author MaxThlon
 */
public interface Window extends delta.common.ui.swing.Container
{
  void pack();
  void toFront();
  void setLocationRelativeTo(java.awt.Component c);
  void setLocationRelativeTo(delta.common.ui.swing.Component c);

  java.awt.Component add(java.awt.Component comp);
  void removeAll();
  void dispose();

  void setIconImages(java.util.List<? extends Image> icons);
  
  void addWindowListener(WindowListener l);
  void removeWindowListener(WindowListener l);
}
