package parsers;

import formula1.Formula1;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MySAXParser extends DefaultHandler {

    private Formula1 formula1;
    private String fileNameXml;
    private String fileNameXsd;

    private SAXParserFactory factory = SAXParserFactory.newInstance();

    private boolean writeParams = false;
    private String nowField = "";
    private String nowClass = "";
    private HashMap<String, String> params = new HashMap<>();
    private List<String> namesClass;


    public MySAXParser(Formula1 formula1, String fileNameXml, String fileNameXsd) {
        this.formula1 = formula1;
        this.fileNameXml = fileNameXml;
        this.fileNameXsd = fileNameXsd;
        this.namesClass = formula1.getClassNames();
    }

    public boolean inizializate() throws SAXException {
        File xsdFile = new File(this.fileNameXsd);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(xsdFile);

        factory.setNamespaceAware(true);
        factory.setSchema(schema);

        return true;
    }

    public void parse() throws ParserConfigurationException, SAXException, IOException {
        File xmlFile = new File(this.fileNameXml);

        SAXParser parser = factory.newSAXParser();
        parser.parse(xmlFile, this);
    }

    @Override
    public void startDocument() {
    }

    // namespaceURI — это пространство имен
    // localName — локальное имя элемента,
    // qName- имя тега/поля
    // atts — атрибуты данного элемента.
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts){
        if(namesClass.contains(qName)){
            //это класс
            nowClass = qName;
            this.writeParams = true;
        }
        else{
            nowField = qName;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if(this.writeParams) {
            params.put(nowField, new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        if (qName.equals(nowClass)) {
            switch (nowClass) {
                case "Pilots":
                    formula1.addNewPilot(params);
                    break;
                case "Bolids":
                    formula1.addNewBolid(params);
                    break;
            }
            nowClass = "";
            nowField = "";
            this.writeParams = false;
            params.clear();
        }
    }

    @Override
    public void endDocument() {
    }
}