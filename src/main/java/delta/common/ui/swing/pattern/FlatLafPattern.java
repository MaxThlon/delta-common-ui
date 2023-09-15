package delta.common.ui.swing.pattern;

import com.formdev.flatlaf.FlatDarkLaf;

/**
 * FlatLafPattern.
 * @author MaxThlon
 */
public class FlatLafPattern extends DefaultGuiPattern {
  
  @Override
  public void initialize() {
    FlatDarkLaf.setup();
  }
}
