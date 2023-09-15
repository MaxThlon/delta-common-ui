package delta.common.ui.swing;

import java.awt.Component
import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle
import java.awt.event.WindowListener
import java.awt.Image

interface DeltaWindow: DeltaComponent {

  fun pack()
  fun toFront()
  fun setLocationRelativeTo(c: Component?)
  fun setLocationRelativeTo(c: DeltaComponent)

  fun setMinimumSize(minimumSize: Dimension)
  fun getPreferredSize(): Dimension
  fun setPreferredSize(preferredSize: Dimension)

  fun add(comp: Component): Component
  fun removeAll()
  fun dispose()

  fun setIconImages(icons: List<Image>)

  fun addWindowListener(l: WindowListener)
  fun removeWindowListener(l: WindowListener)
}
