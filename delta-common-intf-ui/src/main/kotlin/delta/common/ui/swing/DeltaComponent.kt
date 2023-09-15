package delta.common.ui.swing

import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle

interface DeltaComponent {
    fun getWidth(): Int
    fun getHeight(): Int
    fun getSize(): Dimension
    fun setSize(d: Dimension)
    fun setSize(width: Int, height: Int)
    fun setMaximumSize(maximumSize: Dimension)
    fun getBounds(): Rectangle
    fun setBounds(r: Rectangle)
    fun getLocation(): Point
    fun setLocation(x: Int, y: Int)

    fun isVisible(): Boolean
    fun setVisible(b: Boolean)

    fun repaint()
}