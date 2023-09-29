package delta.common.ui.swing.windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import delta.common.ui.swing.DeltaDialog;
import delta.common.ui.swing.DeltaWindow;
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
  public DeltaDialog getDialog()
  {
    return (DeltaDialog)getWindow();
  }

  /**
   * Build the managed window.
   * @return the managed window.
   */
  protected DeltaWindow buildWindow()
  {
    return build();
  }

  protected DeltaDialog build()
  {
    DeltaWindow parentWindow=getParentWindow();

    DeltaDialog dialog=GuiFactory.buildDialog(parentWindow);
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
    DeltaDialog dialog=getDialog();
    dialog.setModal(modal);
    dialog.setVisible(true);
  }

  /**
   * Bring the managed window to front.
   */
  public void bringToFront()
  {
    DeltaDialog dialog=getDialog();
    dialog.setVisible(true);
    dialog.toFront();
  }

  /**
   * Set window title.
   * @param title Title to set.
   */
  public void setTitle(String title)
  {
    DeltaDialog dialog=getDialog();
    dialog.setTitle(title);
  }
}
