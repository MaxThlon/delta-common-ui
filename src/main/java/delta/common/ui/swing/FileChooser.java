package delta.common.ui.swing;

import java.io.File;

/**
 * @author MaxThlon
 */
public interface FileChooser extends delta.common.ui.swing.Component
{
  void setDialogTitle(String dialogTitle);

  File getSelectedFile();
  void setSelectedFile(File file);
  void setFileSelectionMode(int mode);
  void setMultiSelectionEnabled(boolean b);
  void setCurrentDirectory(File dir);

  int showDialog(java.awt.Component c, String approveButtonText);
  int showDialog(delta.common.ui.swing.Component parent, String approveButtonText);
}
