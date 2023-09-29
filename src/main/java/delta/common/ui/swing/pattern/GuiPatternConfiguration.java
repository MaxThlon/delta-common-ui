package delta.common.ui.swing.pattern;

import java.nio.file.Path;
import java.nio.file.Paths;

import delta.common.ui.swing.theme.Theme;
import delta.common.utils.misc.Preferences;
import delta.common.utils.misc.TypedProperties;

/**
 * Configuration for the gui pattern.
 * @author MaxThlon
 */
public class GuiPatternConfiguration
{
  private static final String GUI_PATTERN_CONFIGURATION="GuiPattern";
  private static final String GUI_PATTERN_FACTORY_NAME="patternFactoryName";
  private static final String GUI_PATTERN_NAME="patternName";
  private static final String GUI_PATTERN_THEME_NAME="themeName";
  private static final String GUI_PATTERN_SKIN_PATH="skinPath";
  /**
   * Default gui pattern name
   */
  public static final String DEFAULT_GUI_PATTERN_FACTORY_NAME="Default";
  /**
   * Default gui pattern name
   */
  public static final String DEFAULT_GUI_PATTERN_NAME="Default";
  /**
   * Default theme name
   */
  public static final String DEFAULT_THEME_NAME=null;
  
  /**
   * Default skin name
   */
  public static final String DEFAULT_SKIN_NAME="None";

  private String _guiPatternFactoryName=DEFAULT_GUI_PATTERN_FACTORY_NAME;
  private Theme _theme=new Theme(DEFAULT_GUI_PATTERN_NAME, DEFAULT_THEME_NAME);
  private Path _skinPath=null;

  /**
   * Constructor.
   */
  public GuiPatternConfiguration() {}
  
  
  /**
   * Get the gui pattern factory name.
   * @return a gui pattern factory name.
   */
  public String getGuiPatternFactoryName()
  {
    return _guiPatternFactoryName;
  }

  /**
   * Set the gui pattern factory name.
   * @param guiPatternFactoryName name.
   */
  public void setGuiPatternFactoryName(String guiPatternFactoryName)
  {
    _guiPatternFactoryName=guiPatternFactoryName;
  }

  /**
   * Get the theme.
   * @return {@code Theme} a theme.
   */
  public Theme getTheme()
  {
    return _theme;
  }
  
  /**
   * Set the theme.
   * @param theme {@code Theme}.
   */
  public void setTheme(Theme theme)
  {
    _theme=theme;
  }
  
  /**
   * Get the current skin path.
   * @return a skin path.
   */
  public Path getSkinPath()
  {
    return _skinPath;
  }
  
  /**
   * Set the skin path.
   * @param skinPath path of skin.
   */
  public void setSkinPath(Path skinPath)
  {
    _skinPath=skinPath;
  }

  /**
   * Initialize from preferences.
   * @param preferences Preferences to use.
   */
  public void fromPreferences(Preferences preferences)
  {
    TypedProperties props=preferences.getPreferences(GUI_PATTERN_CONFIGURATION);

    String guiPatternFactoryName=props.getStringProperty(GUI_PATTERN_FACTORY_NAME,getGuiPatternFactoryName());
    setGuiPatternFactoryName(guiPatternFactoryName);
    
    String guiPatternName=props.getStringProperty(GUI_PATTERN_NAME, _theme.getGuiPatternName());
    String themeName=props.getStringProperty(GUI_PATTERN_THEME_NAME, _theme.getThemeName());
    setTheme(new Theme(guiPatternName, themeName));

    Path defaultSkinPath=getSkinPath();
    String skinPath=props.getStringProperty(GUI_PATTERN_SKIN_PATH,(defaultSkinPath!=null)?defaultSkinPath.toString():null);
    setSkinPath((skinPath!=null)?Paths.get(skinPath):null);
  }
  
  /**
   * Save configuration.
   * @param preferences Preferences to use.
   */
  public void save(Preferences preferences)
  {
    TypedProperties props=preferences.getPreferences(GUI_PATTERN_CONFIGURATION);
    props.setStringProperty(GUI_PATTERN_FACTORY_NAME,_guiPatternFactoryName);
    props.setStringProperty(GUI_PATTERN_NAME,_theme.getGuiPatternName());
    if (_theme.getThemeName()==null) {
      props.removeProperty(GUI_PATTERN_THEME_NAME);
    } else {
      props.setStringProperty(GUI_PATTERN_THEME_NAME,_theme.getThemeName());
    }
    if (_skinPath==null) {
      props.removeProperty(GUI_PATTERN_SKIN_PATH);
    } else {
      props.setStringProperty(GUI_PATTERN_SKIN_PATH,_skinPath.toString());
    }
  }
}
