package service.tablecreation;

import static model.TableProperties.COLUMN_SEPARATOR;
import static model.TableProperties.LINE_BREAK;
import static model.TableProperties.WORD_SEPARATOR;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import model.Column;
import model.TableProperties;

/**
 * The implementation of an interface for building a table.
 *
 * @see service.tablecreation.TableBuilder
 * @author Vardan Balaian
 * @created 19.06.2020
 * @since 1.8
 */
@RequiredArgsConstructor
public class TableBuilderImpl implements TableBuilder {

  @NonNull @Getter private final TableProperties tableProperties;

  @Override
  public StringBuilder getFormedColumn(String title, int columnWidth) {
    StringBuilder formedColumn = new StringBuilder(WORD_SEPARATOR).append(title);
    int residualColumnSize = columnWidth - title.length();

    if (residualColumnSize > 0) {
      formedColumn.append(String.join("", Collections.nCopies(residualColumnSize, WORD_SEPARATOR)));
    }

    return formedColumn.append(WORD_SEPARATOR).append(COLUMN_SEPARATOR);
  }

  @Override
  public String getFormedHeaderLine() {
    StringBuilder formedHeaderLine = new StringBuilder(COLUMN_SEPARATOR);

    for (Column column : tableProperties.getColumns()) {
      formedHeaderLine.append(getFormedColumn(column.getTitle(), column.getWidth()));
    }

    return formedHeaderLine.toString() + LINE_BREAK;
  }

  @Override
  public String getFormedContent(String[] data) {
    StringBuilder formedContent = new StringBuilder(COLUMN_SEPARATOR);

    for (int i = 0; i < tableProperties.getColumns().size(); i++) {
      formedContent.append(
          getFormedColumn(data[i], tableProperties.getColumns().get(i).getWidth()));
    }

    return formedContent.toString() + LINE_BREAK;
  }

  @Override
  public int getMaxLineHeight(List<String> lines) {
    AtomicInteger lineHeight = new AtomicInteger(1);
    AtomicInteger columnWidth = new AtomicInteger();

    for (int i = 0; i < lines.size(); i++) {
      columnWidth.set(tableProperties.getColumns().get(i).getWidth());
      if (lineHeight.get() <= lines.get(i).length() / columnWidth.get()) {
        if (lines.get(i).length() % columnWidth.get() == 0 && lines.get(i).length() != 0) {
          lineHeight.set(lines.get(i).length() / columnWidth.get());
        } else if (lines.get(i).length() % columnWidth.get() > 0) {
          lineHeight.set(1 + lines.get(i).length() / columnWidth.get());
        }
      }
    }

    return lineHeight.get();
  }

  @Override
  public int getLinesCount(String str) {
    String[] strings = str.split("\n");
    return strings.length;
  }
}
