package com.rajendarreddyj.basics.xml;

import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * DocumentFactory Support for creating a new XML DOM Document object. Such an object is useful when building a new XML
 * document.
 */
public class DocumentFactory {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static Document getNewDocument(String topTag, final String prefix, final String nameSpace) {
        Document newDoc = null;
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            DOMImplementation docImp = docBuilder.getDOMImplementation();
            if (prefix != null) {
                topTag = prefix + ":" + topTag;
            }
            newDoc = docImp.createDocument(nameSpace, topTag, null);
        } catch (ParserConfigurationException builderExcept) {
            logger.info("ParserConfigurationException: " + builderExcept);
        }
        return newDoc;
    } // getNewDocument

    /**
     * Set the attributes of the Document.
     */
    private static void addSchemaAttributes(final Document doc, final String prefix, final String nameSpace, final String schemaLoc) {
        final String W3_SCHEMA_DEF = "http://www.w3.org/2001/XMLSchema-instance";
        Element docElem = doc.getDocumentElement();
        docElem.setAttribute("xmlns:xsi", W3_SCHEMA_DEF);
        docElem.setAttribute("xmlns", nameSpace);
        if (prefix != null) {
            docElem.setAttribute("xmlns:" + prefix, nameSpace);
        }
        docElem.setAttribute("xsi:schemaLocation", nameSpace + " " + schemaLoc);
    } // addSchemaAttributes

    /**
     * Create a new XML DOM Document object.
     * 
     * @param docTag
     *            the top tag for the document.
     * @param prefix
     *            the XML tag prefix. This paramter may be null.
     * @param nameSpace
     *            the name space for the document. For example http://www.test.com/expression
     * @param schemaLoc
     *            the location of the schema
     */
    public static Document newDocument(final String docTag, final String prefix, final String nameSpace, final String schemaLoc) {
        Document doc = getNewDocument(docTag, prefix, nameSpace);
        if (schemaLoc != null) {
            addSchemaAttributes(doc, prefix, nameSpace, schemaLoc);
        }
        return doc;
    } // newDocument
}
