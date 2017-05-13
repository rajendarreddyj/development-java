package com.rajendarreddyj.basics.xml;

import java.util.logging.Logger;

import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;

/**
 * ParserFactory
 */
public class ParserFactory {
    private static final Logger logger = Logger.getAnonymousLogger();
    /** the namespace feature is true by default */
    final static String NAMESPACES_FEATURE_PREFIX = "http://xml.org/sax/features/namespaces";
    final static String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    final static String VALIDATION_SCHEMA_FEATURE = "http://apache.org/xml/features/validation/schema";

    /**
     * The parser must be instantiated with an error handler. If this is not done, errors and warnings will not be
     * reported. Presumably one can do something more sophisticated than I'm doing here with the error handler. In this
     * case the error handler just prints the fatal error, error or warning.
     */
    private static class LocalErrorHandler implements ErrorHandler {
        private void print(final String kind, final SAXParseException e) {
            logger.info("Parse " + kind + ": " + "Exception = " + e);
        }

        @Override
        public void error(final SAXParseException e) throws SAXParseException {
            this.print("error", e);
            throw e;
        }

        @Override
        public void fatalError(final SAXParseException e) throws SAXParseException {
            this.print("fatalError", e);
            throw e;
        }

        @Override
        public void warning(final SAXParseException e) {
            this.print("warning", e);
        }
    } // LocalErrorHandler

    /**
     * Set a feature in the parser and report any errors.
     */
    private static void setFeature(final DOMParser parser, final String featureURI, final String featureName) {
        try {
            parser.setFeature(featureURI, true);
        } catch (SAXNotSupportedException e) {
            logger.info("ParserFactory::initParser: " + featureName + " not supported by parser");
        } catch (SAXNotRecognizedException e) {
            logger.info("ParserFactory::initParser: " + featureName + " not recognized by parser");
        }
    } // setFeature

    /**
     * Turn on validation in the parser object. The W3C (www.w3.org) and Apache (www.apache.org) documentation is less
     * than clear when it comes to XML Schemas and XML parsers. Initializing a parser so that it performs validation is
     * rather obscure. The features that are set in initialization are URI strings. These are defined on the Apache web
     * page:
     * 
     * <pre>
            http://xml.apache.org/xerces2-j/features.html
     * </pre>
     * <p>
     * A parser feature is turned on by calling the parser method setFeature( featureStr, true ), where featureStr is
     * one of the features defined on the Apache web page. The feature to turn on schema validation is:
     * </p>
     * 
     * <pre>
            http://apache.org/xml/features/validation/schema
     * </pre>
     * <p>
     * Error reporting requries that the basic validation feature be turned on as well. This is:
     * </p>
     * 
     * <pre>
    	    http://xml.org/sax/features/validation
     * </pre>
     * <p>
     * Note that one feature uses apache.org and the other uses xml.org. Apparently this is historical: the base
     * validation feature was originally for DTDs. The validation/schema feature is for XML Schema Descriptions (XSDs).
     * </p>
     * 
     * @param parser
     *            the parser object
     * @param validate
     *            true = turn validation on, false = no validation.
     */
    private static void initParser(final DOMParser parser, final boolean validate) {
        // parser.setErrorHandler(new DefaultHandler());
        parser.setErrorHandler(new LocalErrorHandler());
        if (validate) {
            setFeature(parser, VALIDATION_FEATURE, "VALIDATION_FEATURE");
            setFeature(parser, VALIDATION_SCHEMA_FEATURE, "VALIDATION_SCHEMA_FEATURE");
        }
    } // initParser

    /**
     * Allocate and initialize a new DOM parser.
     * 
     * @param validate
     *            false = no validation, true = initialize the parser for XML schema validation
     */
    public static DOMParser newParser(final boolean validate) {
        DOMParser parser = new DOMParser();
        initParser(parser, validate);
        return parser;
    } // newParser
}
