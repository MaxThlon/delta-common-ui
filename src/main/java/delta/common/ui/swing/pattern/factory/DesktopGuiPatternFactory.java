package delta.common.ui.swing.pattern.factory;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;

import delta.common.ui.swing.Dialog;
import delta.common.ui.swing.Frame;
import delta.common.ui.swing.Window;
import delta.common.ui.swing.GuiFactory;
import delta.common.ui.swing.internalframe.DeltaJDialogInternalFrame;
import delta.common.ui.swing.internalframe.DeltaJFrameInternalFrame;
import delta.common.ui.swing.internalframe.DeltaJInternalFrame;
import delta.common.ui.swing.windows.DeltaJFrame;

/**
 * DesktopGuiPatternFactory.
 * @author MaxThlon
 */
public class DesktopGuiPatternFactory extends BackGroundGuiPatternFactory {
  private Frame _rootFrame=null;
  JDesktopPane _jDesktopPane=null;
  @Override
  public Frame buildFrame() {
    Frame frame=null;

    if (_rootFrame == null) {
      DeltaJFrame deltaJFrame=new DeltaJFrame();
      _jDesktopPane=new JDesktopPane() {
        @Override
        public void paintComponent(Graphics g)
        {
          super.paintComponent(g);
          BufferedImage image=GuiFactory.findAssetImage("background.image");
          if (image != null) {
            Graphics2D g2=(Graphics2D)g;
            g2.setPaint(new TexturePaint(image, new Rectangle(0,0,image.getWidth(),image.getHeight())));
            g2.fillRect(0, 0, getWidth(), getHeight());
          }
        }
      };
      _jDesktopPane.setOpaque(true);
      deltaJFrame.setContentPane(_jDesktopPane);
      deltaJFrame.setPreferredSize(new Dimension(1920,1080));
      deltaJFrame.pack();
      deltaJFrame.setVisible(true);
      _rootFrame=deltaJFrame;

      DeltaJFrameInternalFrame internalFrame=new DeltaJFrameInternalFrame() {
        @Override
        public void setDefaultCloseOperation(int operation) {
          deltaJFrame.setDefaultCloseOperation(operation);
        }
        @Override
        public void addWindowListener(WindowListener l) {
          deltaJFrame.addWindowListener(l);
        }
        @Override
        public void removeAll() {
          super.removeAll();
          deltaJFrame.removeAll();
        }
        @Override
        public void dispose() {
          super.dispose();
          deltaJFrame.dispose();
        }
        @Override
        public void setIconImages(java.util.List<? extends Image> icons){
          deltaJFrame.setIconImages(icons);
          super.setIconImages(icons);
        }
        @Override
        public void setTitle(String value){
          deltaJFrame.setTitle(value);
          super.setTitle(value);
        }
        @Override
        public JMenuBar getJMenuBar(){
          return deltaJFrame.getJMenuBar();
        }
        @Override
        public void setJMenuBar(JMenuBar m){
          deltaJFrame.setJMenuBar(m);
        }
      };
      internalFrame.setResizable(true);
      internalFrame.setMaximizable(true);
      internalFrame.setOpaque(false);
      _jDesktopPane.add(internalFrame);
      frame=internalFrame;
    } else {
      DeltaJFrameInternalFrame internalFrame=new DeltaJFrameInternalFrame();
      internalFrame.setClosable(true);
      internalFrame.setResizable(true);
      internalFrame.setMaximizable(true);
      internalFrame.setOpaque(false);
      _jDesktopPane.add(internalFrame);
      frame=internalFrame;
    }

    return frame;
  }
  
  @Override
  public Dialog buildDialog(Window owner) {
    DeltaJInternalFrame ownerInternalFrame=
        (owner != null) && (owner instanceof DeltaJInternalFrame)?(DeltaJInternalFrame)owner:null;
    
    DeltaJDialogInternalFrame internalFrame=new DeltaJDialogInternalFrame(ownerInternalFrame);
    internalFrame.setClosable(true);
    internalFrame.setResizable(true);
    internalFrame.setMaximizable(true);
    internalFrame.setOpaque(false);

    _jDesktopPane.add(internalFrame); 

    return internalFrame;
  }
}