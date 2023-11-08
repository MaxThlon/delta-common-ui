package delta.common.ui.swing;

import java.awt.Container;
import java.awt.Dialog.ModalityType;

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

  ModalityType getModalityType();
  void setModalityType(ModalityType type);
  boolean isModal();
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
