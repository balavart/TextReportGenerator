package service.tablecreation;

import java.util.List;

/**
 * The interface for building a table.
 *
 * @author Vardan Balaian
 * @created 19.06.2020
 * @since 1.8
 */
public interface TableBuilder {

  /**
   * Gets the formed column.
   *
   * @param title the title
   * @param columnWidth the column width
   * @return the formed column
   */
  StringBuilder getFormedColumn(String title, int columnWidth);

  /**
   * Gets the formed header line.
   *
   * @return the formed header line
   */
  String getFormedHeaderLine();

  /**
   * Gets formed content.
   *
   * @param data the data
   * @return the formed content
   */
  String getFormedContent(String[] data);

  /**
   * Gets max line height.
   *
   * @param lines the lines
   * @return the max line height
   */
  int getMaxLineHeight(List<String> lines);

  /**
   * Gets lines count.
   *
   * @param str the string
   * @return the lines count
   */
  int getLinesCount(String str);

  /**
   * Gets table properties. Used by Lombok.
   *
   * @return the table properties
   */
  model.TableProperties getTableProperties();
}
