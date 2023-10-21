package delta.common.ui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * @author MaxThlon
 */
@SuppressWarnings("javadoc")
public interface Component
{
  int getWidth();
  int getHeight();
  Dimension getSize();
  void setSize(Dimension d);
  void setSize(int width, int height);
  Dimension getMinimumSize();
  void setMinimumSize(Dimension minimumSize);
  Dimension getMaximumSize();
  void setMaximumSize(Dimension maximumSize);
  Dimension getPreferredSize();
  void setPreferredSize(Dimension preferredSize);
  Rectangle getBounds();
  void setBounds(Rectangle r);
  Point getLocation();
  void setLocation(int x,int y);

  boolean isVisible();
  void setVisible(boolean b);

  Color getBackground();
  void setBackground(Color bgColor);

  void repaint();
}
