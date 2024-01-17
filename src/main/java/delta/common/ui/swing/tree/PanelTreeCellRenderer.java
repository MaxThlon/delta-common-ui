package delta.common.ui.swing.tree;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import delta.common.ui.swing.panels.PanelController;

/**
 * TableTreeViewCellRenderer library for lua scripts.
 * @author MaxThlon
 */
public class PanelTreeCellRenderer extends DefaultTreeCellRenderer {
  private Dimension _panelPreferredSize;

  /**
   * 
   */
  public PanelTreeCellRenderer() {
    super();
  }

  @Override
  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean xselected,
                                                boolean expanded, boolean leaf, int row, boolean xhasFocus) {
    Component component;
    DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
    if (node.getUserObject() instanceof PanelController) {
      PanelController panelController=(PanelController)node.getUserObject();

      component=panelController.getPanel();
      panelController.getPanel().setPreferredSize(_panelPreferredSize);
    } else {
      component=super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
    }

    return component;
  }

  /**
   * @param preferredSize .
   */
  public void setPanelPreferredSize(Dimension preferredSize) {
    _panelPreferredSize=preferredSize;
  }
}
