package delta.common.ui.swing;

import java.awt.Container;

import javax.swing.JLayeredPane;
import javax.swing.JRootPane;

/**
 * @author MaxThlon
 */
@SuppressWarnings("javadoc")
public interface JDialog extends delta.common.ui.swing.Window
{
  void setTitle(String title);
  void setResizable(boolean resizable);

  void setModal(boolean modal);
  void setDefaultCloseOperation(int operation);

  JRootPane getRootPane();
  void setLayeredPane(JLayeredPane layeredPane);
  JLayeredPane getLayeredPane();
  void setGlassPane(java.awt.Component glassPane);
  java.awt.Component getGlassPane();
  Container getContentPane();
  void setContentPane(Container contentPane);
}
