package service.parsers;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * The implementation of an interface for input and output of txt files.
 *
 * @see TxtIo
 * @author Vardan Balaian
 * @created 20.06.2020
 * @since 1.8
 */
public class TxtIoImpl implements TxtIo {

  @Override
  public void addTxtFile(String text, String destination) {
    File txtResult = new File(destination);

    try {
      Files.write(text.getBytes(StandardCharsets.UTF_16), txtResult);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void consoleTxtFileOutput(String txtPath) {
    File txtFile = new File(txtPath);

    try {
      Files.readLines(txtFile, StandardCharsets.UTF_16).forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
