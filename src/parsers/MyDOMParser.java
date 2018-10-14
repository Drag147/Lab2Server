package parsers;

import essence.Bolids;
import essence.Pilots;
import formula1.Formula1;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyDOMParser {

    private String fileNameXml;
    private String fileNameXsd;

    final private List<String> namesClass;
    private List<String> filedNames = new ArrayList<>();

    private HashMap<String, String> params = new HashMap<>();

    public MyDOMParser (Formula1 formula1, String fileNameXml, String fileNameXsd){
        this.fileNameXml = fileNameXml;
        this.fileNameXsd = fileNameXsd;
        this.namesClass = formula1.getClassNames();
    }

    public boolean parse(Formula1 formula1)
    {
        try
        {
            File xmlFile = new File(this.fileNameXml);
            File xsdFile = new File(this.fileNameXsd);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsdFile);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setSchema(schema);
            factory.setNamespaceAware(true);

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            for (String nameClass : namesClass) {
                NodeList listNodes = doc.getElementsByTagName(nameClass);

                for (int i = 0; i < listNodes.getLength(); i++) {
                    Node node = listNodes.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        //System.out.println("Node name: " + node.getNodeName());
                        switch (element.getNodeName()) {
                            case "Pilots":
                                params.clear();
                                this.filedNames = Pilots.fieldClassNames;

                                for (String fieldName: this.filedNames) {
                                    params.put(fieldName, element.getElementsByTagName(fieldName).item(0).getTextContent());
                                }
                                formula1.addNewPilot(params);
//                                paramsClass.add(element.getElementsByTagName("Personal_pilot_number").item(0).getTextContent());
//                                paramsClass.add(element.getElementsByTagName("Name").item(0).getTextContent());
//                                paramsClass.add(element.getElementsByTagName("Surname").item(0).getTextContent());
//                                paramsClass.add(element.getElementsByTagName("Date_of_birth").item(0).getTextContent());
//                                System.out.println(element.getElementsByTagName("Personal_pilot_number").item(0).getTextContent());
//                                System.out.println(element.getElementsByTagName("Name").item(0).getTextContent());
//                                System.out.println(element.getElementsByTagName("Surname").item(0).getTextContent());
//                                System.out.println(element.getElementsByTagName("Date_of_birth").item(0).getTextContent());
                                break;
                            case "Bolids":
                                params.clear();

                                this.filedNames = Bolids.fieldClassNames;

                                for (String fieldName: this.filedNames) {
                                    params.put(fieldName, element.getElementsByTagName(fieldName).item(0).getTextContent());
                                }

                                formula1.addNewBolid(params);
//                                paramsClass.add(element.getElementsByTagName("Name_bolid").item(0).getTextContent());
//                                paramsClass.add(element.getElementsByTagName("Name_engine").item(0).getTextContent());
//                                paramsClass.add(element.getElementsByTagName("Name_chassis").item(0).getTextContent());
//                                paramsClass.add(element.getElementsByTagName("Year_bolid").item(0).getTextContent());
//                                System.out.println(element.getElementsByTagName("Name_bolid").item(0).getTextContent());
//                                System.out.println(element.getElementsByTagName("Name_engine").item(0).getTextContent());
//                                System.out.println(element.getElementsByTagName("Name_chassis").item(0).getTextContent());
//                                System.out.println(element.getElementsByTagName("Year_bolid").item(0).getTextContent());
                                break;
                        }
//                        System.out.println();
                    }
                }
            }
            return true;

        } catch (ParserConfigurationException | SAXException | IOException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
}
