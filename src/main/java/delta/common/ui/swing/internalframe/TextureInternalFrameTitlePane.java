package delta.common.ui.swing.internalframe;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JInternalFrame;

import com.formdev.flatlaf.ui.FlatInternalFrameTitlePane;

import delta.common.ui.swing.GuiFactory;

/**
 * LotroInternalFrameTitlePane used inside {@link delta.common.ui.swing.internalframe.TextureInternalFrameUI LotroInternalFrameUI}.
 * @author MaxThlon
 */
public class TextureInternalFrameTitlePane extends FlatInternalFrameTitlePane {
  private int _imageHeight;
  private int _mainPanelTop;
  private int _paneHeight;

  /**
   * @param f JInternalFrame
   */
  public TextureInternalFrameTitlePane(JInternalFrame f) {
    super(f);
  }

  @Override
  public Dimension getPreferredSize() {
    if (_paneHeight > 0) return getMinimumSize();
    return super.getPreferredSize();
  }

  @Override
  public Dimension getMinimumSize() {
    if (_paneHeight > 0) {
      Dimension d = new Dimension(super.getMinimumSize());
      d.height = _paneHeight + 2;
      return d;
    }

    return super.getMinimumSize();
  }
  
  /**
   * @return drawPaneHeight
   */
  public int mainPanelTop() {
    return _mainPanelTop;
  }

  @Override
  protected void installDefaults() {
    // Load images to cache
    BufferedImage bufferedImage = GuiFactory.findAssetImage("base_box_titlebar_top");
    if (bufferedImage != null) {
      _imageHeight=bufferedImage.getHeight();
      _mainPanelTop = (int)(_imageHeight*1.3f);
      _paneHeight = (int)(_imageHeight*1.5f);
      GuiFactory.findAssetImage("base_box_titlebar_left");
      GuiFactory.findAssetImage("base_box_titlebar_right");
    }
    
    super.installDefaults();
  }
  
  @Override
  protected void addSubComponents() {
    super.addSubComponents();
    if (_paneHeight > 0) {
      GridBagLayout gridBagLayout=new GridBagLayout();
      GridBagConstraints c=new GridBagConstraints(1,0,3,2,1.0,1.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0);
      gridBagLayout.addLayoutComponent(this.getComponent(0), c);
  
      c.gridx=2;
      c.gridy=1;
      c.anchor=GridBagConstraints.SOUTHEAST;
      gridBagLayout.addLayoutComponent(this.getComponent(1), c);
      setLayout(gridBagLayout);
      revalidate();
    }
  }
  
  @Override
  protected void updateFrameIcon() {
    if (_paneHeight == 0) super.updateFrameIcon();
  }

  @Override
  protected void paintTitleBackground(Graphics g) {
    if (_paneHeight > 0) {
      BufferedImage image = GuiFactory.findAssetImage("base_box_titlebar_top");
      if (image != null) {
        int xMiddle=getWidth() / 2;
        int xTitlebarMiddle = image.getWidth() / 2;
        int top=image.getHeight()/4;
        g.drawImage(image, xMiddle - xTitlebarMiddle, top, null);
        
        image = GuiFactory.findAssetImage("base_box_titlebar_left");
        if (image != null) g.drawImage(image, xMiddle - xTitlebarMiddle - image.getWidth(), top, null);
        
        image = GuiFactory.findAssetImage("base_box_titlebar_right");
        if (image != null) g.drawImage(image, xMiddle + xTitlebarMiddle, top, null);
      } else {
        super.paintTitleBackground(g);
      }
    } else {
      super.paintTitleBackground(g);
    }
  }
}
