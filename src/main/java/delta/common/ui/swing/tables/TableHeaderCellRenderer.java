package delta.common.ui.swing.tables;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * A table header cell renderer that uses a tooltip.
 * @author DAM
 */
public class TableHeaderCellRenderer extends DefaultTableCellRenderer
{
  /**
   * Constructor.
   */
  public TableHeaderCellRenderer()
  {
    super();
    setBorder(UIManager.getBorder("TableHeader.cellBorder"));
    setHorizontalAlignment(SwingConstants.CENTER);
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
  {
    Component ret=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
    if (ret instanceof JComponent)
    {
      String text=(value==null)?"":value.toString();
      ((JComponent)ret).setToolTipText(text);
    }
    return this;
  }
}
