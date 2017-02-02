package com.rajendarreddyj.basics.xml;

/**
 * ExpToXML
 * 
 */
public class ExpToXML {
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
                System.out.println("Expression parse error:" + e);
            }
            System.out.println(xml);
        }
    }
}
