package delta.common.ui.swing.tree;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author MaxThlon
 */
public class ExpandableMutableTreeNode extends DefaultMutableTreeNode
{
  /**
   * @param userObject .
   */
  public ExpandableMutableTreeNode(Object userObject) {
    super(userObject);
}
  @Override
  public boolean isLeaf() {
    return false;
  }
}
