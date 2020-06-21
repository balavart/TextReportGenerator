package service.parsers;

/**
 * The interface for input and output of txt files.
 *
 * @author Vardan Balaian
 * @created 20.06.2020
 * @since 1.8
 */
public interface TxtIo {

  /**
   * Add txt file based on String.
   *
   * @param text the text
   * @param destination the destination to save the txt file
   */
  void addTxtFile(String text, String destination);

  /**
   * Console txt file output.
   *
   * @param txtPath the txt file path
   */
  void consoleTxtFileOutput(String txtPath);
}
