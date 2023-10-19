package delta.common.ui.swing.tree;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;

import delta.common.ui.swing.panels.PanelController;

/**
 * LuaTreeCellEditor library for lua scripts.
 * @author MaxThlon
 */
public class PanelTreeCellEditor extends DefaultTreeCellEditor   implements TreeCellEditor {
  private PanelController _panelController;

  /**
   * Constructor
   * @param tree .
   * @param renderer .
   */
  public PanelTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
    super(tree, renderer);
  }

  @Override
  protected boolean canEditImmediately(EventObject event) {
    if((event instanceof MouseEvent) &&
      ((MouseEvent)event).getButton() != 0) {
      MouseEvent       me = (MouseEvent)event;

      return ((me.getClickCount() >= 1) &&
          inHitRegion(me.getX(), me.getY()));
    }
    return (event == null);
  }

  /*@Override
  public boolean isCellEditable(EventObject e) {
    return super.isCellEditable(e) &&
        (((DefaultMutableTreeNode) lastPath.getLastPathComponent())
            .getUserObject() instanceof PanelController);
  }*/
  
  @Override
  public Object getCellEditorValue() {
    return _panelController;
  }

  @Override
  public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected,
                                              boolean expanded, boolean leaf, int row) {
    _panelController=null;
    DefaultMutableTreeNode node=(DefaultMutableTreeNode)value;
    Component component = null;
    if (node.getUserObject() instanceof PanelController) {
      _panelController=(PanelController)node.getUserObject();
      
      component = _panelController.getPanel();
    } else {
      component=super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
    }

    return component;
  }

}