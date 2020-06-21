package model;

import java.util.Collections;
import java.util.List;
import lombok.Getter;

/**
 * The storage of properties and methods for building a table.
 *
 * @author Vardan Balaian
 * @created 18.06.2020
 * @since 1.8
 */
public class TableProperties {

  public static final String WORD_SEPARATOR = " ";
  public static final String COLUMN_SEPARATOR = "|";
  public static final String PAGE_SEPARATOR = "~";
  public static final String LINE_BREAK_CHAR = "-";
  public static final String LINE_BREAK = "\n";

  private final Settings settings;

  @Getter private final int width;
  @Getter private final int height;
  @Getter private final String lineSeparator;

  /**
   * Takes table settings, initializes table properties and line separator.
   *
   * @param settings table settings
   */
  public TableProperties(Settings settings) {
    this.settings = settings;
    this.width = getWidthMeasurements();
    this.height = getHeightMeasurements();
    this.lineSeparator = getFormedLineSeparator();
  }

  private int getWidthMeasurements() {
    return settings.getPage().getWidth();
  }

  private int getHeightMeasurements() {
    return settings.getPage().getHeight();
  }

  private String getFormedLineSeparator() {
    return String.join("", Collections.nCopies(getWidthMeasurements(), LINE_BREAK_CHAR))
        + LINE_BREAK;
  }

  public List<Column> getColumns() {
    return settings.getColumns();
  }
}
