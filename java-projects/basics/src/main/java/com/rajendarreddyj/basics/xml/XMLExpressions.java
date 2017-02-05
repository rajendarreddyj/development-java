package com.rajendarreddyj.basics.xml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.xerces.parsers.DOMParser;

/**
 * An interactive expression interpreter.
 * <p>
 * An expression (x = a + b * c, for example) is converted to XML and then evaluated. The expression evaluator supports
 * a symbol table that allows assignment and retrieval of symbol values. Only integer expressions are supported.
 * </p>
 */
public class XMLExpressions {
    static final String TOP_TAG = "EXPRESSION";
    static final String NAME_SPACE = "http://www.test.com/expression";
    static final String PREFIX = "ex";

    public static void main(final String[] argv) {
        String line = "";
        int len = 0;
        ParseExpToXML xmlParse = new ParseExpToXML();
        EvalXML evalStmtOrExp = new EvalXML();
        DOMParser parser = ParserFactory.newParser(true);
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            do {
                System.out.print("> ");
                line = is.readLine();
                if (line != null) {
                    line = line.trim();
                    len = line.length();
                    if (len > 0) {
                        try {
                            String xml = xmlParse.parse(line, TOP_TAG, PREFIX, NAME_SPACE, "xmlexpr/expression.xsd");
                            Integer rslt = evalStmtOrExp.eval(parser, xml.getBytes());
                            if (rslt != null) {
                                System.out.println(rslt);
                            }
                        } catch (ExpParseException e1) {
                            System.out.println("Expression parse error: " + e1);
                        }
                    }
                }
            } while ((line != null) && (len > 0));
        } catch (IOException e2) {
            System.out.println("IOException: " + e2.getMessage());
        }
    } // main
}
