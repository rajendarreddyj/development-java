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

    private void usage() {
        String name = this.getClass().getName();
        System.out.println("usage: " + name + "<XML fileName>");
    }

    private byte[] fileToBytes(final String xmlFileName) {
        byte[] bytes = null;
        try {
            InputStream in = new FileInputStream(xmlFileName);
            bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("fileToBytes: could not find file " + xmlFileName);
        } catch (IOException e) {
            System.out.println("fileToBytes: error reading file " + xmlFileName);
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
            System.out.println(msg);
        } catch (IOException e) {
            System.out.println("TestXerces.bytesToDocument: IOException = " + e);
        }
        return doc;
    } // bytesToDocument

    private TestXerces(final String[] args) {
        if (args.length == 1) {
            String xmlFileName = args[0];
            System.out.println("Validating XML file " + xmlFileName);

            byte[] xmlBytes = this.fileToBytes(xmlFileName);
            Document doc = this.bytesToDocument(xmlFileName, xmlBytes, true);
            if (doc != null) {
                System.out.println("Validation OK, DOM object created");
            } else {
                System.out.println("Validation failed");
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
