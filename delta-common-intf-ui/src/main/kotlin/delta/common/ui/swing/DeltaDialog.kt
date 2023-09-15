package delta.common.ui.swing;

import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import javax.swing.JLayeredPane
import javax.swing.JRootPane
import delta.common.ui.swing.DeltaWindow

interface DeltaDialog: DeltaWindow {
  fun setTitle(title: String)
  fun setResizable(resizable: Boolean)

  fun setModal(modal: Boolean)
  fun setDefaultCloseOperation(operation: Int)

  fun getRootPane(): JRootPane
  fun setLayeredPane(layeredPane: JLayeredPane)
  fun getLayeredPane(): JLayeredPane
  fun setGlassPane(glassPane: Component)
  fun getGlassPane(): Component
  fun getContentPane(): Container
  fun setContentPane(contentPane: Container)
}
