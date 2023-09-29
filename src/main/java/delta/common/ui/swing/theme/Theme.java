package delta.common.ui.swing.theme;

import java.util.Objects;

/**
 * Class Theme.
 * @author MaxThlon
 */
public class Theme {
  private String _guiPatternName;
  private String _themeName;
  
  /**
   * @param guiPatternName
   * @param themeName
   */
  public Theme(String guiPatternName, String themeName) {
    _guiPatternName=guiPatternName;
    _themeName=themeName;
  }
  
  /**
   * @return GuiPatternName
   */
  public String getGuiPatternName() {
    return _guiPatternName;
  }
  
  /**
   * @return ThemeName
   */
  public String getThemeName() {
    return _themeName;
  }
  
  /**
   * @return "GuiPattern name -> ThemeName"
   */
  public String formatGuiPatternNameAndThemeName() {
    
    return (_themeName == null)?_guiPatternName : String.format("%s -> %s", _guiPatternName, _themeName);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Theme) {
      Theme theme=(Theme)obj;
      return Objects.equals(this._guiPatternName, theme._guiPatternName) &&
             Objects.equals(this._themeName, theme._themeName);
    }
    return false;
}
}
