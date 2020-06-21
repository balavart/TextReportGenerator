package service.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * tsv files parsing interface implementation.
 *
 * @see TsvParsingMachine
 * @author Vardan Balaian
 * @created 18.06.2020
 * @since 1.8
 */
public class TsvParsingMachineImpl implements TsvParsingMachine {

  @Override
  public Map<Integer, List<String>> getSourceData(String filePath) {
    File file = new File(filePath);
    BufferedReader reader = null;

    try {
      reader =
          new BufferedReader(
              new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_16));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    String dataLine;
    Map<Integer, List<String>> lines = new HashMap<>();
    AtomicInteger linesCount = new AtomicInteger();

    try {
      while ((dataLine = Objects.requireNonNull(reader).readLine()) != null) {
        String[] strings = dataLine.split("\t");
        lines.put(linesCount.getAndIncrement(), new ArrayList<>(Arrays.asList(strings)));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return lines;
  }
}
