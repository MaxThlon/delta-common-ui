package delta.common.ui.swing.pattern;

import java.lang.reflect.InvocationTargetException;

import delta.common.utils.misc.Preferences;

/**
 * Base class for GuiPatternManager it's derived inside {@code GuiFactory}
 * into {@code GuiPatternManagerPrivate} to prevent call to {@code initialize},
 * use {@code GuiFactory.updatePattern} to activate a {@code GuiPattern}
 * @author MaxThlon
 */
public abstract class GuiPatternManager  {
  private GuiPattern _uiPattern=null;
  
  /**
   * Constructor
   */
  public GuiPatternManager() {
  }

  /**
   * @return current GuiPattern
   */
  public GuiPattern getGuiPattern() {
    return this._uiPattern;
  }

  /**
   * Initialize a {@code GuiPattern} from {@code Preferences},
   * Uninitialize {@code GuiPattern} if one is already active.
   * @param preferences 
   * @return newly created {@code GuiPattern.GuiPatternFactory} 
   */
  protected GuiPattern.GuiPatternFactory initialize(Preferences preferences) {
    GuiPattern.GuiPatternFactory guiPatternFactory = null;

    String name = "FlatLaf";
    switch (name) {
      case "FlatLaf":
        this._uiPattern=new FlatLafPattern();
        break;
      case "Background":
      default: {
        this._uiPattern=new BackGroundGuiPattern();
      }
    }

    this._uiPattern.initialize();

    try {
      guiPatternFactory=this._uiPattern.getGuiPatternFactoryClass().getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | 
             IllegalArgumentException | InvocationTargetException |
             NoSuchMethodException | SecurityException bigError) {
      bigError.printStackTrace();
      guiPatternFactory=new BackGroundGuiPattern.BackGroundGuiFactory();
    }

    return guiPatternFactory;
  }
}
