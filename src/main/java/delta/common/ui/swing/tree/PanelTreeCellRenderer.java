package delta.common.ui.swing.tree;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import delta.common.ui.swing.panels.PanelController;

/**
 * TableTreeViewCellRenderer library for lua scripts.
 * @author MaxThlon
 */
public class PanelTreeCellRenderer extends DefaultTreeCellRenderer {

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

      component = panelController.getPanel();
    } else {
      component=super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
      /*JLabel l = (JLabel)super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
      l.setFont(tree.getFont().deriveFont(leaf ? 16f : 48f));*/
      //height = tree.getRowHeight();
      //height = leaf ? 20 : 60;
    }
    return component;
  }
}
