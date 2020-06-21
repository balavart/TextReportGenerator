package service.parsers;

import model.Settings;

/**
 * The interface for xml files parsing.
 *
 * @author Vardan Balaian
 * @created 18.06.2020
 * @since 1.8
 */
public interface XmlParsingMachine {

  /**
   * Gets settings.
   *
   * @param filePath the file path
   * @return the settings
   */
  Settings getSettings(String filePath);
}
