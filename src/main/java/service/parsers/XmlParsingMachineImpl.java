package service.parsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.Column;
import model.Page;
import model.Settings;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * xml files parsing interface implementation.
 *
 * @see XmlParsingMachine
 * @author Vardan Balaian
 * @created 18.06.2020
 * @since 1.8
 */
public class XmlParsingMachineImpl implements XmlParsingMachine {

  @Override
  public Settings getSettings(String filePath) {
    File xmlDoc = new File(filePath);
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(xmlDoc);

      Page page = getPage(document);
      List<Column> columns = getColumnContents(document);

      return new Settings() {
        {
          setPage(page);
          setColumns(columns);
        }
      };

    } catch (ParserConfigurationException | IOException | SAXException e) {
      e.printStackTrace();
    }

    return null;
  }

  private List<Column> getColumnContents(Document document) {
    NodeList settingsNodeList = document.getElementsByTagName("column");
    List<Column> columns = new ArrayList<>();

    for (int i = 0; i < settingsNodeList.getLength(); i++) {
      Node node = settingsNodeList.item(i);

      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element element = (Element) node;
        columns.add(
            new Column() {
              {
                setTitle(element.getElementsByTagName("title").item(0).getTextContent());
                setWidth(
                    Integer.parseInt(
                        element.getElementsByTagName("width").item(0).getTextContent()));
              }
            });
      }
    }
    return columns;
  }

  private Page getPage(Document document) {
    NodeList settingsNodeList = document.getElementsByTagName("page");
    Page page = new Page();

    for (int i = 0; i < settingsNodeList.getLength(); i++) {
      Node node = settingsNodeList.item(i);

      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element element = (Element) node;
        page.setWidth(
            Integer.parseInt(element.getElementsByTagName("width").item(0).getTextContent()));
        page.setHeight(
            Integer.parseInt(element.getElementsByTagName("height").item(0).getTextContent()));
      }
    }
    return page;
  }
}
