package delta.common.ui.swing;

import java.awt.LayoutManager;

/**
 * @author MaxThlon
 */
@SuppressWarnings("javadoc")
public interface Container extends delta.common.ui.swing.Component
{
  public LayoutManager getLayout();
  public void setLayout(LayoutManager mgr);
}
