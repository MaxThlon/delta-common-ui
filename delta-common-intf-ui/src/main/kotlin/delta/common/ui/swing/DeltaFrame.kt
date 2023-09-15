package delta.common.ui.swing;

import java.awt.Container
import javax.swing.JMenuBar;

interface DeltaFrame: DeltaWindow {
  fun setTitle(title: String)

  fun setResizable(resizable: Boolean)

  fun setContentPane(contentPane: Container)
  fun getContentPane(): Container
  fun setJMenuBar(menubar: JMenuBar)

  fun setState(state: Int)
  fun setDefaultCloseOperation(operation: Int)
}
