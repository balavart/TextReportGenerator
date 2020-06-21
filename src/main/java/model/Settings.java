package model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Mapping entity of Settings.
 *
 * @author Vardan Balaian
 * @created 18.06.2020
 * @since 1.8
 */
@Data
public class Settings {

  private Page page;
  private List<Column> columns;

  public Settings() {
    page = new Page();
    columns = new ArrayList<>();
  }
}
