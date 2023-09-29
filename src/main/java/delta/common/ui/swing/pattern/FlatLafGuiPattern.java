package delta.common.ui.swing.pattern;

import java.util.Arrays;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.formdev.flatlaf.FlatLaf;

import delta.common.ui.swing.theme.Theme;

/**
 * FlatLafPattern.
 * @author MaxThlon
 */
public class FlatLafGuiPattern extends BackGroundGuiPattern {
  private static BooleanSupplier DEFAULT_THEME=com.formdev.flatlaf.FlatLightLaf::setup;
  /**
   * Map for custom FlatLaf
   */
  public static List<ImmutablePair<String, BooleanSupplier>> THEME_LIST = 
      Arrays.asList(
        new ImmutablePair<String, BooleanSupplier>("FlatLaf Light", DEFAULT_THEME),
        new ImmutablePair<String, BooleanSupplier>("FlatLaf Dark", com.formdev.flatlaf.FlatDarkLaf::setup),
        new ImmutablePair<String, BooleanSupplier>("FlatLaf macOS Light", com.formdev.flatlaf.themes.FlatMacLightLaf::setup),
        new ImmutablePair<String, BooleanSupplier>("FlatLaf macOS Dark", com.formdev.flatlaf.themes.FlatMacDarkLaf::setup)
      );

  /**
   * @return list of themes names
   */
  public static List<String> getThemeNames() {
    return THEME_LIST.stream().map(pair -> pair.left).collect(Collectors.toList());
  }

  protected boolean setupFlatLaf(String themeName) {
    return (themeName == null)? 
        DEFAULT_THEME.getAsBoolean() :
        THEME_LIST.stream()
          .filter(pair -> themeName.equals(pair.left))
          .map(pair -> pair.right)
          .findFirst()
          .orElse(DEFAULT_THEME)
          .getAsBoolean();
  }

  @Override
  public void initialize(Theme theme) {
    FlatLaf.registerCustomDefaultsSource("gui.themes");
    setupFlatLaf(theme.getThemeName());
  }
  
  @Override
  public void uninitialize() {
    FlatLaf.unregisterCustomDefaultsSource("gui.themes");
  }
}
