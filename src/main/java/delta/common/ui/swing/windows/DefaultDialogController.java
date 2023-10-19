package delta.common.ui.swing.windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import delta.common.ui.swing.Dialog;
import delta.common.ui.swing.Window;
import delta.common.ui.swing.GuiFactory;
import delta.common.ui.swing.icons.ApplicationIcons;

/**
 * Default dialog controller.
 * @author DAM
 */
public class DefaultDialogController extends AbstractWindowController
{
  /**
   * Constructor.
   * @param parent Parent controller.
   */
  public DefaultDialogController(WindowController parent)
  {
    super(parent);
  }

  /**
   * Get the managed dialog.
   * @return the managed dialog.
   */
  public Dialog getDialog()
  {
    return (Dialog)getWindow();
  }

  /**
   * Build the managed window.
   * @return the managed window.
   */
  protected Window buildWindow()
  {
    return build();
  }

  protected Dialog build()
  {
    Window parentWindow=getParentWindow();

    Dialog dialog=GuiFactory.buildDialog(parentWindow);
    _window=dialog;
    JPanel backgroundPanel=GuiFactory.buildBackgroundPanel(new BorderLayout());
    dialog.setContentPane(backgroundPanel);
    List<Image> icons=ApplicationIcons.getApplicationIcons();
    dialog.setIconImages(icons);
    Container contentPane=dialog.getContentPane();
    JComponent component=buildContents();
    if (component!=null)
    {
      contentPane.add(component,BorderLayout.CENTER);
    }
    
    return dialog;
  }

  public void show()
  {
    show(false);
  }

  /**
   * Show the managed window.
   * @param modal Modality of the managed dialog.
   */
  public void show(boolean modal)
  {
    Dialog dialog=getDialog();
    dialog.setModal(modal);
    dialog.setVisible(true);
  }

  /**
   * Bring the managed window to front.
   */
  public void bringToFront()
  {
    Dialog dialog=getDialog();
    dialog.setVisible(true);
    dialog.toFront();
  }

  /**
   * Set window title.
   * @param title Title to set.
   */
  public void setTitle(String title)
  {
    Dialog dialog=getDialog();
    dialog.setTitle(title);
  }
}
