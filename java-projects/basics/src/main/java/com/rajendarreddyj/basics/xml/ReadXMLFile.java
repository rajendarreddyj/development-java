package com.rajendarreddyj.basics.xml;

import java.io.File;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * The DOM is the easiest to use Java XML Parser. It parses an entire XML document and load it into memory, modeling it
 * with Object for easy nodel traversal. DOM Parser is slow and consume a lot memory if it load a XML document which
 * contains a lot of data.
 */
/*
 * example to show you how to read a XML file in Java via DOM XML parser. The DOM interface is the easiest XML parser to
 * understand, and use. It parses an entire XML document and load it into memory, modeling it with Object for easy
 * traversal or manipulation. Note DOM Parser is slow and consume a lot of memory if it load a XML document which
 * contains a lot of data. Please consider SAX parser as solution for it, SAX is faster than DOM and use less memory.
 */
public class ReadXMLFile {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String argv[]) {
        try {
            File fXmlFile = new File("/home/rajendarreddy/test/file.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            logger.info("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("staff");
            logger.info("-----------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    logger.info("First Name : " + getTagValue("firstname", eElement));
                    logger.info("Last Name : " + getTagValue("lastname", eElement));
                    logger.info("Nick Name : " + getTagValue("nickname", eElement));
                    logger.info("Salary : " + getTagValue("salary", eElement));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTagValue(final String sTag, final Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = nlList.item(0);
        return nValue.getNodeValue();
    }
}
