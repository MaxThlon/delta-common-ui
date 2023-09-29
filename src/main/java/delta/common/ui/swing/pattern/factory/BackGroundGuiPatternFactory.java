package delta.common.ui.swing.pattern.factory;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import delta.common.ui.swing.BackgroundPanel;
import delta.common.ui.swing.GuiFactory;

/**
 * BackGroundGuiPatternFactory.
 * @author MaxThlon
 */
public class BackGroundGuiPatternFactory extends DefaultGuiPatternFactory {

  @Override
  public Paint getBackgroundPaint()
  {
    BufferedImage backgroundImage=GuiFactory.findAssetImage("background.image");
    if (backgroundImage != null) {
      Rectangle r=new Rectangle(0,0,backgroundImage.getWidth(),backgroundImage.getHeight());
      return new TexturePaint(backgroundImage,r);
    }
    return super.getBackgroundPaint();
  }
  
  @Override
  public JPanel buildBackgroundPanel(LayoutManager layout) {
    return new BackgroundPanel("background.image", layout);
  }
  
  @Override
  public Border createBevelBorder() {
    return BorderFactory.createBevelBorder(BevelBorder.LOWERED, UIManager.getColor("foreground"), Color.GRAY);
  }
}
