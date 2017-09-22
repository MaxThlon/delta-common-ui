package delta.common.ui.swing.lists;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import delta.common.ui.swing.GuiFactory;

/**
 * List controller.
 * @param <T> Type of managed items.
 */
public class ListController<T>
{
  private JList<T> _list;
  private DefaultListModel<T> _model;
  private Comparator<T> _comparator;
  private List<T> _items;

  /**
   * Constructor.
   * @param comparator Comparator to sort displayed items, may be
   * <code>null</code>.
   */
  public ListController(Comparator<T> comparator)
  {
    _comparator=comparator;
    _items=new ArrayList<T>();
    buildList();
  }

  private void buildList()
  {
    _list=GuiFactory.buildList();
    Dimension d=new Dimension(300,300);
    _list.setSize(d);
    _list.setMinimumSize(d);

    _model=new DefaultListModel<T>();
    for(T item:_items)
    {
      _model.addElement(item);
    }
    _list.setModel(_model);
  }

  /**
   * Get the managed JList.
   * @return the managed JList.
   */
  public JList<T> getList()
  {
    return _list;
  }

  /**
   * Get all the managed items.
   * @return a possibly empty but not <code>null</code> list of items.
   */
  public List<T> getItems()
  {
    return _items;
  }

  /**
   * Get the selected items.
   * @return a possibly empty but not <code>null</code> list of items.
   */
  public List<T> getSelectedItems()
  {
    List<T> items=new ArrayList<T>();
    if (_list!=null)
    {
      int[] selection=_list.getSelectedIndices();
      for(int index:selection)
      {
        items.add(_items.get(index));
      }
    }
    return items;
  }

  /**
   * Set the items to use.
   * @param list The items to use.
   */
  public void setItems(List<T> list)
  {
    _items.clear();
    _items.addAll(list);
    if (_comparator!=null)
    {
      Collections.sort(_items,_comparator);
    }
    if (_model!=null)
    {
      _model.clear();
      for(T item:_items)
      {
        _model.addElement(item);
      }
    }
  }

  /**
   * Add an item.
   * The item is added at the end of the list, or sorted if a comparator is used.
   * @param item Item to add.
   */
  public void addItem(T item)
  {
    _items.add(item);
    if (_comparator!=null)
    {
      Collections.sort(_items,_comparator);
      if (_model!=null)
      {
        int index=_items.indexOf(item);
        _model.add(index,item);
      }
    }
    else
    {
      if (_model!=null)
      {
        _model.addElement(item);
      }
    }
  }

  /**
   * Remove an item.
   * @param t Item to remove.
   */
  public void removeItem(T t)
  {
    _items.remove(t);
    if (_model!=null)
    {
      _model.removeElement(t);
    }
  }

  /**
   * Remove some items.
   * @param items Items to remove.
   */
  public void removeItems(List<T> items)
  {
    for(T item:items)
    {
      removeItem(item);
    }
  }

  /**
   * Remove all items.
   */
  public void removeAll()
  {
    _items.clear();
    if (_model!=null)
    {
      _model.clear();
    }
  }

  /**
   * Release all managed resources.
   */
  public void dispose()
  {
    _list=null;
  }
}
