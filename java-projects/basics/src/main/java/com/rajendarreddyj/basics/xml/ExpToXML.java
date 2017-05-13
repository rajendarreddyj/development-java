package com.rajendarreddyj.basics.xml;

import java.util.logging.Logger;

/**
 * ExpToXML
 * 
 */
public class ExpToXML {
    private static final Logger logger = Logger.getAnonymousLogger();
    static final String TOP_TAG = "EXPRESSION";
    static final String NAME_SPACE = "http://www.test.com/expression";
    static final String PREFIX = "ex";

    public static void main(final String[] args) {
        if (args.length == 1) {
            ParseExpToXML xmlParse = new ParseExpToXML();
            String expr = args[0];
            String xml = "";
            try {
                xml = xmlParse.parse(expr, TOP_TAG, PREFIX, NAME_SPACE, "expression.xsd");
            } catch (ExpParseException e) {
                logger.info("Expression parse error:" + e);
            }
            logger.info(xml);
        }
    }
}
