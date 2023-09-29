package delta.common.ui.swing.pattern;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import com.formdev.flatlaf.util.SoftCache;

import delta.common.ui.ImageUtils;
import delta.common.ui.swing.icons.IconsManager;
import delta.common.ui.swing.pattern.factory.BackGroundGuiPatternFactory;
import delta.common.ui.swing.pattern.factory.DefaultGuiPatternFactory;
import delta.common.ui.swing.pattern.factory.DesktopGuiPatternFactory;
import delta.common.ui.swing.pattern.factory.GuiPatternFactory;
import delta.common.ui.swing.theme.Theme;
import delta.common.utils.skin.Skin;

/**
 * GuiPatternManager
 * @author MaxThlon
 */
public class GuiPatternManager {
  /**
   * Map for custom GuiPatternFactories
   */
  public static Map<String, Supplier<GuiPatternFactory>> _guiPatternFactoryMap =
      new HashMap<String, Supplier<GuiPatternFactory>>() {{
        put("Default", DefaultGuiPatternFactory::new);
        put("Background", BackGroundGuiPatternFactory::new);
        put("Desktop", DesktopGuiPatternFactory::new);
      }
  };
  
  /*
   * Default GuiPatten supplier.
   */
  private static Supplier<GuiPattern> DEFAULT_GUI_PATTERN=DefaultGuiPattern::new;
  /**
   * Map for custom GuiPatterns
   */
  protected static List<ImmutableTriple<String, Supplier<GuiPattern>, Supplier<List<String>>>> _guiPatternList =
      new ArrayList<ImmutableTriple<String, Supplier<GuiPattern>, Supplier<List<String>>>>() {{
          add(new ImmutableTriple<String, Supplier<GuiPattern>, Supplier<List<String>>>(
              "Default", DEFAULT_GUI_PATTERN, null
          ));
          add(new ImmutableTriple<String, Supplier<GuiPattern>, Supplier<List<String>>>(
              "Background", BackGroundGuiPattern::new, null
          ));
          add(new ImmutableTriple<String, Supplier<GuiPattern>, Supplier<List<String>>>(
              "FlatLaf", FlatLafGuiPattern::new, FlatLafGuiPattern::getThemeNames
          ));
      }};

  protected SoftCache<String, BufferedImage> _imageCache = new SoftCache<>();
  protected GuiPatternConfiguration _guiPatternConfiguration;
  protected GuiPattern _guiPattern=null;
  protected Skin _skin=null;

  /**
   * Constructor
   * @param guiPatternConfiguration {@code GuiPatternConfiguration}
   */
  public GuiPatternManager(GuiPatternConfiguration guiPatternConfiguration) {
    _guiPatternConfiguration = guiPatternConfiguration;
  }

  /**
   * Get the guiPattern list.
   * @return .
   */
  public Set<String> getGuiPatternFactories()
  {
    Set<String> guipatternFactories=new HashSet<String>(_guiPatternFactoryMap.keySet());
    return guipatternFactories;
  }

  /**
   * Get the themes list containing [GuiPatternNameAndThemeName, ThemeName, Supplier<GuiPattern>].
   * Accumulate all unique GuiPatterns names and GuiPatterns with themes list.
   * @return .
   */
  public List<Theme> getThemes()
  {
    return _guiPatternList.stream().map((guiPatternTriple) -> {
          String guiPatternName=guiPatternTriple.left;
          Supplier<List<String>> supplier=guiPatternTriple.right;
          if (supplier != null) {
            return supplier.get().stream().map((themeName) ->
              new Theme(guiPatternName, themeName)
            ).collect(Collectors.toList());
          }
          return Arrays.asList(
              new Theme(guiPatternName, null)
          );
        }).flatMap(Collection::stream).collect(Collectors.toList());
  }

  /**
   * Get the skin list.
   * @return .
   */
  public List<ImmutablePair<String, Path>> getSkins()
  {
    List<ImmutablePair<String, Path>> skins=loadSkinList();
    skins.add(0, new ImmutablePair<String,Path>(GuiPatternConfiguration.DEFAULT_SKIN_NAME, null));
    return skins;
  }

  /**
   * @return current GuiPattern
   */
  public GuiPattern getGuiPattern() {
    return _guiPattern;
  }

  /**
   * @return {@code GuiPatternFactory} instance
   */
  public GuiPatternFactory newGuiPatternFactory() {
    GuiPatternFactory guiPatternFactory = _guiPatternFactoryMap
        .getOrDefault(_guiPatternConfiguration.getGuiPatternFactoryName(), DefaultGuiPatternFactory::new)
        .get();
    return guiPatternFactory;
  }

  /**
   * Initialize a {@code GuiPattern} from {@code Preferences},
   */
  public void initialize() {
    GuiPattern oldGuiPattern=_guiPattern;
    Theme theme=_guiPatternConfiguration.getTheme();
    _guiPattern=_guiPatternList.stream()
        .filter(guiPatternTriple -> Objects.equals(theme.getGuiPatternName(), guiPatternTriple.left))
        .findFirst()
        .map(guiPatternTriple -> (guiPatternTriple != null)?guiPatternTriple.middle:null)
        .orElse(DEFAULT_GUI_PATTERN)
        .get();

    if (oldGuiPattern != null) oldGuiPattern.uninitialize();
    _guiPattern.initialize(theme);
  }

  /**
   * Get the skin art asset path.
   * @param imageAssetId
   * @return .
   */
  public Path getAssetImagePath(String imageAssetId)
  {
    Path artAssetPath=null;
    if (_skin!=null) {
      artAssetPath=_skin._mappings.getOrDefault(imageAssetId,null);
      if (artAssetPath != null) {
        artAssetPath=_guiPatternConfiguration.getSkinPath().resolve(artAssetPath);
      }
    }
    
    return artAssetPath;
  }
  
  protected BufferedImage loadAssetImage(String imageAssetId) {
    String path=UIManager.getString(imageAssetId);
    if (path != null) {
      return IconsManager.getImage(path);
    } else {
      Path artAssetPath=getAssetImagePath(imageAssetId);
      if (artAssetPath != null) {
        return ImageUtils.loadImage(artAssetPath.toFile());
      }
    }
    return null;
  }

  protected void loadAssetImages(Map<String, BufferedImage> imageCache, Stream<String> imageAssetIds) {
    imageAssetIds.parallel().collect(
        HashMap<String, BufferedImage>::new,
        (m, imageAssetId) -> imageCache.put(imageAssetId, loadAssetImage(imageAssetId)),
        (m1, m2) -> imageCache.putAll(m2)
    );
  }
  
  /**
   * Find or create a {@code ImageIcon} using the given iconAssetId.
   * @param iconAssetId
   * @return a {@code BufferedImage}.
   */
  public ImageIcon findAssetIcon(String iconAssetId) {
    String path=UIManager.getString(iconAssetId);
    if (path != null) {
      return IconsManager.getIcon(path);
    }
    return null;
  }
  
  /**
   * Load and cache {@code BufferedImage} using the given imageAssetIds.
   * @param imageAssetIds
   */
  public void loadAssetImages(Stream<String> imageAssetIds) {
    loadAssetImages(_imageCache, imageAssetIds);
  }
  
  /**
   * Find or create a {@code BufferedImage} using the given imageAssetId.
   * @param imageAssetId 
   * @return a {@code BufferedImage}.
   */
  public BufferedImage findAssetImage(String imageAssetId) {
    BufferedImage image=_imageCache.get(imageAssetId);
    if (image == null) {
      image=loadAssetImage(imageAssetId);
      if (image != null) {
        _imageCache.put(imageAssetId, image);
      }
    }
    return image;
  }
  
  /**
   * Reload ArtAssets.
   */
  public void reloadAssets() {
    SoftCache<String, BufferedImage> imageCache = new SoftCache<>();
    loadAssetImages(imageCache, _imageCache.keySet().stream());
    _imageCache=imageCache;
  }
  
  /**
   * Load skin list data.
   * @return Map of skins names and path.
   */
  protected List<ImmutablePair<String, Path>> loadSkinList() {
    return new ArrayList<ImmutablePair<String,Path>>();
  }

  /**
   * Load skin data.
   */
  public void loadSkin() {}
  
}
