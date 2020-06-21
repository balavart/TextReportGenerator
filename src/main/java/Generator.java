import java.util.List;
import java.util.Map;
import model.Paths;
import model.Settings;
import model.TableProperties;
import service.parsers.TsvParsingMachine;
import service.parsers.TsvParsingMachineImpl;
import service.parsers.TxtIo;
import service.parsers.TxtIoImpl;
import service.parsers.XmlParsingMachine;
import service.parsers.XmlParsingMachineImpl;
import service.tablecreation.TableBuilder;
import service.tablecreation.TableBuilderImpl;
import view.Table;

/**
 * Main start class of the application.
 *
 * @author Vardan Balaian
 * @created 18.06.2020
 * @since 1.8
 */
public class Generator {
  public static void main(String[] args) {
    new Generator().startApp();
  }

  private void startApp() {
    XmlParsingMachine xmlParsingMachine = new XmlParsingMachineImpl();
    TsvParsingMachine tsvParsingMachine = new TsvParsingMachineImpl();

    Settings settings = xmlParsingMachine.getSettings(Paths.SETTING_PATH);
    Map<Integer, List<String>> sourceData = tsvParsingMachine.getSourceData(Paths.SOURCE_DATA_PATH);
    TableProperties tableProperties = new TableProperties(settings);
    TableBuilder tableBuilder = new TableBuilderImpl(tableProperties);

    Table table = new Table(tableBuilder, sourceData);

    TxtIo txtIO = new TxtIoImpl();
    txtIO.addTxtFile(table.getFormedTable(), Paths.TXT_FILE_PATH);
    txtIO.consoleTxtFileOutput(Paths.TXT_FILE_PATH);
  }
}
