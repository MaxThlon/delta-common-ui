package delta.common.ui.swing.internalframe;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.plaf.ComponentUI;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatInternalFrameUI;

import delta.common.ui.swing.GuiFactory;

/**
 * LotroInternalFrameUI extends {@link javax.swing.plaf.InternalFrameUI InternalFrameUI}
 * for custom design of {@link javax.swing.JInternalFrame JInternalFrame}
 * @author MaxThlon
 */
public class TextureInternalFrameUI extends FlatInternalFrameUI {
  
  public static ComponentUI createUI( JComponent c ) {
    return new TextureInternalFrameUI( (JInternalFrame) c );
  }

  /**
   * @param b {@Code JInternalFrame}
   */
  public TextureInternalFrameUI(JInternalFrame b) {
    super(b);
  }
  
  @Override
  protected JComponent createNorthPane( JInternalFrame w ) {
    return new TextureInternalFrameTitlePane( w );
  }
  
  /**
   * @author MaxThlon
   */
  public static class TextureInternalFrameBorder extends FlatEmptyBorder {

    /**
     * Constructor
     */
    public TextureInternalFrameBorder() {
      super(36/2, 36/2, 36/2, 36/2);

      // Load images to cache
      GuiFactory.loadAssetImages(Stream.of(
          "box_silver_upper_left_light",
          "box_silver_upper_right_light",
          "box_silver_bottom_left_light",
          "box_silver_lower_right_light",
          "box_silver_upper_light",
          "box_silver_side_right_light",
          "box_silver_bottom_light",
          "box_silver_side_left_light"
          //"base_box_center_silver_light"
      ));
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      JInternalFrame jInternalFrame = (JInternalFrame) c;
      TextureInternalFrameTitlePane lotroInternalFrameTitlePane = (TextureInternalFrameTitlePane)((TextureInternalFrameUI)jInternalFrame.getUI()).getNorthPane();
      Graphics2D g2 = (Graphics2D)g;

      BufferedImage image = GuiFactory.findAssetImage("box_silver_upper_left_light");
      if (image != null ) {
        int cornerWidth = image.getWidth();
        int cornderHeight = image.getHeight();
        int frameTop = lotroInternalFrameTitlePane.mainPanelTop();
        int sideBorderTop = frameTop + (cornderHeight / 2);
        
        
        g.drawImage(image, 0, frameTop, null);
        image = GuiFactory.findAssetImage("box_silver_upper_right_light");
        if (image != null ) g.drawImage(image, width - image.getWidth(), frameTop, null);
        image = GuiFactory.findAssetImage("box_silver_lower_right_light");
        if (image != null ) g.drawImage(image, width - image.getWidth(), height - image.getHeight(), null);
        image = GuiFactory.findAssetImage("box_silver_bottom_left_light");
        if (image != null ) g.drawImage(image, 0, height - image.getHeight(), null);
 
        image = GuiFactory.findAssetImage("box_silver_upper_light");
        if (image != null ) {
          g2.setPaint(new TexturePaint(image, new Rectangle(cornerWidth, frameTop, image.getWidth(), image.getHeight())));
          g2.fillRect(cornerWidth, frameTop, width - (cornerWidth + cornerWidth), image.getHeight());
        }
        
        image = GuiFactory.findAssetImage("box_silver_side_right_light");
        if (image != null ) {
          g2.setPaint(new TexturePaint(image, new Rectangle(width - image.getWidth(), sideBorderTop, image.getWidth(), image.getHeight())));
          g2.fillRect(width - image.getWidth(), sideBorderTop, image.getWidth(), height - (frameTop + cornderHeight));
        }

        image = GuiFactory.findAssetImage("box_silver_bottom_light");
        if (image != null ) {
          g2.setPaint(new TexturePaint(image, new Rectangle(cornerWidth, height - image.getHeight(), image.getWidth(), image.getHeight())));
          g2.fillRect(cornerWidth, height - image.getHeight(), width - (cornerWidth + cornerWidth), image.getHeight());
        }

        image = GuiFactory.findAssetImage("box_silver_side_left_light");
        if (image != null ) {
          g2.setPaint(new TexturePaint(image, new Rectangle(0, sideBorderTop, image.getWidth(), image.getHeight())));
          g2.fillRect(0, sideBorderTop, image.getWidth(), height - (frameTop + cornderHeight));
        }
      }// else super.paintBorder(c, g, x, y, width, height);
    }
  }
}
