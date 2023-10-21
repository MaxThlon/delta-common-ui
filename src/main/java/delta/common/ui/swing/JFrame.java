package delta.common.ui.swing;

import java.awt.Container;

import javax.swing.JMenuBar;

/**
 * @author MaxThlon
 */
@SuppressWarnings("javadoc")
public interface JFrame extends delta.common.ui.swing.Window
{
  String getTitle();
  void setTitle(String title);

  void setResizable(boolean resizable);

  void setContentPane(Container contentPane);
  Container getContentPane();
  void setJMenuBar(JMenuBar menubar);

  void setState(int state);
  void setDefaultCloseOperation(int operation);
}
