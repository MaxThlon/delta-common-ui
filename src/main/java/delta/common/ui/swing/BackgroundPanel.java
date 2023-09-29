package delta.common.ui.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Panel that shows an image.
 * @author DAM
 */
public class BackgroundPanel extends JPanel
{
  private Image _image=null;
  private String _imageAssetId=null;

  /**
   * Constructor.
   * @param image Image to display.
   * @param layout Layout manager.
   */
  public BackgroundPanel(Image image, LayoutManager layout)
  {
    _image=image;
    setLayout(layout);
  }

  /**
   * Constructor.
   * @param imageAssetId art Asset ID for Image to display.
   * @param layout Layout manager.
   */
  public BackgroundPanel(String imageAssetId, LayoutManager layout)
  {
    _imageAssetId=imageAssetId;
    setLayout(layout);
  }

  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    if (_image != null) {
      int width=getWidth();
      int height=getHeight();
      int imageWidth=_image.getWidth(null);
      int imageHeight=_image.getHeight(null);
      if ((imageHeight==-1) || (imageWidth==-1))
      {
        return;
      }
      int x=0;
      while (x<width) {
        int y=0;
        while (y<height) {
          g.drawImage(_image,x,y,null);
          y+=imageHeight;
        }
        x+=imageWidth;
      }
    } else if (_imageAssetId != null) {
      BufferedImage image = GuiFactory.findAssetImage(_imageAssetId);
      if (image != null) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(new TexturePaint(image, new Rectangle(0,0,image.getWidth(),image.getHeight())));
        g2.fillRect(0, 0, getWidth(), getHeight());
      }
    }
  }
}