package view;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import model.TableProperties;
import service.tablecreation.TableBuilder;

/**
 * Final assembly class of the final table.
 *
 * @author Vardan Balaian
 * @created 19.06.2020
 * @since 1.8
 */
@RequiredArgsConstructor
public class Table {

  @NonNull private final TableBuilder tableBuilder;
  @NonNull private final Map<Integer, List<String>> sourceData;

  /**
   * Gets formed table. To build a table, table builder components and a data source are used.
   *
   * @return the formed table
   */
  public String getFormedTable() {
    StringBuilder demonstrativeTable =
        new StringBuilder(tableBuilder.getFormedHeaderLine())
            .append(tableBuilder.getTableProperties().getLineSeparator());

    AtomicInteger separatorShiftAmount = new AtomicInteger();
    AtomicInteger columnCheckPointer = new AtomicInteger();

    for (int i = 0; i < sourceData.size(); i++) {
      List<String> lines = sourceData.get(i);
      separatorShiftAmount.set(0);
      int maxLineHeight = tableBuilder.getMaxLineHeight(lines);

      for (int j = 0; j < maxLineHeight; j++) {
        String[] lineContent = new String[lines.size()];

        for (int k = 0; k < lines.size(); k++) {
          int columnWidth = tableBuilder.getTableProperties().getColumns().get(k).getWidth();

          if (lines.get(k).length() < columnWidth) {
            if (j == 0) {
              lineContent[k] = lines.get(k);
            } else {
              lineContent[k] = "";
            }
          } else {
            columnCheckPointer.set(columnWidth * j + separatorShiftAmount.get());
            if (columnCheckPointer.get() >= lines.get(k).length()) {
              lineContent[k] = "";
            } else {
              if (lines.get(k).charAt(columnCheckPointer.get()) == ' ') {
                columnCheckPointer.getAndIncrement();
                separatorShiftAmount.getAndIncrement();
              }
              if ((columnWidth + columnCheckPointer.get()) < lines.get(k).length()) {
                lineContent[k] =
                    lines
                        .get(k)
                        .substring(
                            columnCheckPointer.get(), columnWidth + columnCheckPointer.get());
              } else {
                lineContent[k] = lines.get(k).substring(columnCheckPointer.get());
              }
            }
          }
        }

        demonstrativeTable.append(tableBuilder.getFormedContent(lineContent));

        if (tableBuilder.getLinesCount(demonstrativeTable.toString())
            == tableBuilder.getTableProperties().getHeight()) {
          demonstrativeTable
              .append(TableProperties.PAGE_SEPARATOR)
              .append(TableProperties.LINE_BREAK)
              .append(tableBuilder.getFormedHeaderLine())
              .append(tableBuilder.getTableProperties().getLineSeparator());
        } else if (j == maxLineHeight - 1) {
          demonstrativeTable.append(tableBuilder.getTableProperties().getLineSeparator());
        }
      }
    }

    return demonstrativeTable.toString();
  }
}
