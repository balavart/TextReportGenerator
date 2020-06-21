package service.parsers;

import java.util.List;
import java.util.Map;

/**
 * The interface for tsv files parsing.
 *
 * @author Vardan Balaian
 * @created 18.06.2020
 * @since 1.8
 */
public interface TsvParsingMachine {

  /**
   * Gets source data. The line number acts as the key, and the array of words of this line acts as
   * the value.
   *
   * @param filePath the file path
   * @return the source data as a Map
   */
  Map<Integer, List<String>> getSourceData(String filePath);
}
