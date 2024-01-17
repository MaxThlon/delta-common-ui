package delta.common.ui.swing.tree;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import delta.common.ui.swing.GuiFactory;

/**
 * @author MaxThlon
 */
public class GenericTreeController
{
  // Data
  //private DataProvider<POJO> _dataProvider;
  // GUI
  private JTree _tree;
  /**
   * Constructor.
   */
  public GenericTreeController(/*DataProvider<POJO> dataProvider*/)
  {
    //_dataProvider=dataProvider;
  }
  
  /**
   * Get the managed table.
   * @return the managed table.
   */
  public JTree getTree()
  {
    if (_tree==null)
    {
      _tree=build();
      //applySort(_sort);
    }
    return _tree;
  }
  
  private JTree build()
  {
    final JTree tree=GuiFactory.buildTree(null);
    tree.setEditable(true);
    tree.setRootVisible(false);
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    tree.setShowsRootHandles(true);
    return tree;
  }

  /**
   * Clear tree.
   */
  public void clear()
  {
    if (_tree!=null)
    {
      DefaultTreeModel treeModel = (DefaultTreeModel)_tree.getModel();
      DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)treeModel.getRoot();
      rootNode.removeAllChildren();
      reload();
    }
  }

  /**
   * Reload tree.
   */
  public void reload()
  {
    if (_tree!=null)
    {
      DefaultTreeModel treeModel = (DefaultTreeModel)_tree.getModel();
      treeModel.reload();
    }
  }

  /**
   * Release all managed resources.
   */
  public void dispose()
  {
    // Preferences
    /*if (_preferencesMgr!=null)
    {
      _preferencesMgr.savePreferences();
      _preferencesMgr.dispose();
      _preferencesMgr=null;
    }*/
    // GUI
    if (_tree!=null)
    {
      _tree=null;
    }
    // Data
    //_dataProvider=null;
  }
}
