package delta.common.ui.swing

import delta.common.ui.swing.DeltaWindow
import java.awt.Component
import java.io.File

interface DeltaFileChooser: DeltaComponent {
    fun setDialogTitle(dialogTitle: String?)

    var selectedFile: File?
    fun setFileSelectionMode(mode: Int)
    fun setMultiSelectionEnabled(b: Boolean)
    fun setCurrentDirectory(dir: File?)

    fun showDialog(c: Component?, approveButtonText: String?): Int
    fun showDialog(parent: DeltaComponent, approveButtonText: String?): Int
}
