/**
 * \file To compile and run this class you need the following "jars" in your CLASSPATH:
 * 
 * xercesImpl.jar xml-apis.jar xmlParserAPIs.jar
 * 
 */
package com.rajendarreddyj.basics.xml;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * TestXerces
 * 
 */
public class TestXerces {
    private static final Logger logger = Logger.getAnonymousLogger();
    private void usage() {
        String name = this.getClass().getName();
        logger.info("usage: " + name + "<XML fileName>");
    }

    private byte[] fileToBytes(final String xmlFileName) {
        byte[] bytes = null;
        try {
            InputStream in = new FileInputStream(xmlFileName);
            bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
        } catch (FileNotFoundException e) {
            logger.info("fileToBytes: could not find file " + xmlFileName);
        } catch (IOException e) {
            logger.info("fileToBytes: error reading file " + xmlFileName);
        }
        return bytes;
    } // fileToBytes

    /**
     * Create a DOM object from the contents of an XML file.
     * 
     * @param xmlFiileName
     *            the name of the file containing the XML document
     * @param validate
     *            true = validate using a schema defined in the document false = do not validate.
     */
    private Document bytesToDocument(final String xmlFileName, final byte[] xmlBytes, final boolean validate) {
        DOMParser parser = ParserFactory.newParser(true);
        Document doc = null;
        try {
            ByteArrayInputStream istream = new ByteArrayInputStream(xmlBytes);
            InputSource isource = new InputSource(istream);
            parser.parse(isource);
            doc = parser.getDocument();
        } catch (SAXException e) {
            String msg = null;
            if (e instanceof SAXParseException) {
                int lineNum = ((SAXParseException) e).getLineNumber();
                int columnNumber = ((SAXParseException) e).getColumnNumber();
                String exceptionMsg = ((SAXParseException) e).getMessage();
                msg = xmlFileName + "(" + lineNum + ", " + columnNumber + "): " + exceptionMsg;
            } else {
                msg = "TestXerces.bytesToDocument: SAX Exception = " + e;
            }
            logger.info(msg);
        } catch (IOException e) {
            logger.info("TestXerces.bytesToDocument: IOException = " + e);
        }
        return doc;
    } // bytesToDocument

    private TestXerces(final String[] args) {
        if (args.length == 1) {
            String xmlFileName = args[0];
            logger.info("Validating XML file " + xmlFileName);
            byte[] xmlBytes = this.fileToBytes(xmlFileName);
            Document doc = this.bytesToDocument(xmlFileName, xmlBytes, true);
            if (doc != null) {
                logger.info("Validation OK, DOM object created");
            } else {
                logger.info("Validation failed");
            }
        } // args.length == 1
        else {
            this.usage();
        }
    } // TestXerces constructor

    public static void main(final String[] args) {
        new TestXerces(args);
    }
}
