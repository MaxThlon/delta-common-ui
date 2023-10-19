package delta.common.ui.swing.pattern;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import delta.common.ui.swing.theme.Theme;
import delta.common.utils.application.config.path.ApplicationPathConfiguration;
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
  
  private static final Path SKINS_PATH=Paths.get("skins");
  /**
   * Default skin name
   */
  public static final String DEFAULT_SKIN_NAME="None";
  private static String SKIN_DEFINITION_FILENAME="SkinDefinition.xml";

  private ApplicationPathConfiguration _userPathConfiguration;
  private String _guiPatternFactoryName=DEFAULT_GUI_PATTERN_FACTORY_NAME;
  private Theme _theme=new Theme(DEFAULT_GUI_PATTERN_NAME, DEFAULT_THEME_NAME);
  protected Path _skinPath=null;

  /**
   * Constructor.
   * @param userPathConfiguration .
   */
  public GuiPatternConfiguration(ApplicationPathConfiguration userPathConfiguration) {
    _userPathConfiguration=userPathConfiguration;
  }
  
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
   * Get the user data path for skins.
   * @return a directory path.
   */
  public Path getSkinsPath()
  {
    return _userPathConfiguration.getUserPath().resolve(SKINS_PATH);
  }

  /**
   * Get the current skin path.
   * @return a skin path.
   */
  public Path getSkinPath()
  {
    Path skinsPath=getSkinsPath();
    return ((skinsPath != null) && (_skinPath != null))?skinsPath.resolve(_skinPath):null;
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
   * Get the current skin path.
   * @return a skin path.
   */
  public File getSkinDefinitionFile()
  {
    Path skinPath=getSkinPath();
    return (skinPath != null)?getSkinPath().resolve(SKIN_DEFINITION_FILENAME).toFile():null;
  }

  /**
   * Initialize from preferences.
   * @param preferences Preferences to use.
   */
  public void fromPreferences(Preferences preferences)
  {
    TypedProperties props=preferences.getPreferences(GUI_PATTERN_CONFIGURATION);
    FromProperties(props);
  }
  
  /**
   * Initialize from typed properties.
   * @param props
   */
  protected void FromProperties(TypedProperties props) {
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
    save(props);
  }
  
  /**
   * Save typed properties.
   * @param props typed properties to use.
   */
  protected void save(TypedProperties props)
  {
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
