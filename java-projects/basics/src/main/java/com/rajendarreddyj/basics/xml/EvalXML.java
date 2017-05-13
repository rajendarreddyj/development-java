package com.rajendarreddyj.basics.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.rajendarreddyj.basics.enums.TokenType;

/**
 * EvalXML
 *
 * 
 * Support for evaluating expressions in XML form.
 * <p>
 * A validated DOM Object is built for the XML expression. The recursive methods in this class walk over the DOM tree
 * and evaluate the expression.
 * </p>
 * <p>
 * The tree walk reflects the structure of the expression. The expression structure is shown below in BNF like syntax.
 * </p>
 * 
 * <pre>
        statement: assignment | addExp

        assignment: ident "=" addExp

        addExp: term | addExp addOp addExp

        term: unaryExpr | term mulOp term

        unaryExpr: factor | minus factor

        factor: ident | number | "(" addExp ")"
 * </pre>
 * <p>
 * The XML expression is validated by an XML schema (see expression.xsd).
 * </p>
 * <p>
 * Expressions may be evaluated by themselves or assigned to a variable. The class supports a basic symbol table to
 * support symbols and their associated values.
 * </p>
 * 
 */
public class EvalXML {
    private static final Logger logger = Logger.getAnonymousLogger();
    /**
     * The symbol table consists of a set of String, Integer pairs, where the String object is the key.
     */
    private HashMap<String, Integer> mSymTab = new HashMap<>();

    /**
     * Parse the XML into a DOM Document
     */
    private static Document bytesToDocument(final DOMParser parser, final byte[] xmlBytes) throws SAXException, IOException {
        ByteArrayInputStream istream = new ByteArrayInputStream(xmlBytes);
        InputSource isource = new InputSource(istream);
        parser.parse(isource);
        Document xmlDoc = parser.getDocument();
        return xmlDoc;
    } // bytesToDocument

    /**
     * A DOM NodeList may include a variety of different node types. This is awkward when it comes to expression
     * processing, since most of the time we only care about ELEMENT_NODEs. The toElementNodeList method builds an
     * ArrayList that consists only of ELEMENT_NODES.
     * 
     * @param list
     *            a NodeList of DOM Node objects
     * 
     * @return return a Vector of ELEMENT_TYPE nodes. If there are no element type nodes, the size() of the Vector will
     *         zero.
     * 
     */
    private ArrayList<Node> toElementNodeList(final NodeList list) {
        ArrayList<Node> elemList = new ArrayList<>();
        if (list != null) {
            int len = list.getLength();
            for (int i = 0; i < len; i++) {
                if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    elemList.add(list.item(i));
                }
            }
        }
        return elemList;
    } // toElementNodeList

    /**
     * Given a symbol name, look the symbol up in the symbol table.
     * 
     * @return the value of the symbol, or 0 if the symbol is not found.
     */
    private int symbolLookup(final String name) {
        int symVal = 0;
        Integer i = this.mSymTab.get(name);
        if (i != null) {
            symVal = i.intValue();
        } else {
            logger.info("No symbol found for " + name);
        }
        return symVal;
    } // symbolLookup

    /**
     * Enter a symbol and its value into the symbol table
     * 
     * @param name
     *            symbol name
     * @param value
     *            symbol value
     */
    private void symbolEnter(final String name, final int value) {
        Integer i = new Integer(value);
        this.mSymTab.put(name, i);
    } // symbolEnter

    /**
     * Get the type name associated with the numeric Node type value. This method is called from error messages.
     */
    private String nodeTypeToString(final short type) {
        String typeName = "Unknow Node type";
        if (type == Node.ATTRIBUTE_NODE) {
            typeName = "ATTRIBUTE_NODE";
        } else if (type == Node.CDATA_SECTION_NODE) {
            typeName = "CDATA_SECTION_NODE";
        } else if (type == Node.COMMENT_NODE) {
            typeName = "COMMENT_NODE";
        } else if (type == Node.DOCUMENT_FRAGMENT_NODE) {
            typeName = "DOCUMENT_FRAGMENT_NODE";
        } else if (type == Node.DOCUMENT_NODE) {
            typeName = "DOCUMENT_NODE";
        } else if (type == Node.DOCUMENT_TYPE_NODE) {
            typeName = "DOCUMENT_TYPE_NODE";
        } else if (type == Node.ELEMENT_NODE) {
            typeName = "ELEMENT_NODE";
        } else if (type == Node.ENTITY_NODE) {
            typeName = "ENTITY_NODE";
        } else if (type == Node.ENTITY_REFERENCE_NODE) {
            typeName = "ENTITY_REFERENCE_NODE";
        } else if (type == Node.NOTATION_NODE) {
            typeName = "NOTATION_NODE";
        } else if (type == Node.PROCESSING_INSTRUCTION_NODE) {
            typeName = "PROCESSING_INSTRUCTION_NODE";
        } else if (type == Node.TEXT_NODE) {
            typeName = "TEXT_NODE ";
        }
        return typeName;
    } // nodeTypeToString

    /**
     * Evaluate identifiers, numbers or parenthesized expressions (via a recursive call to the addExp method).
     * 
     * <pre>
           factor: ident | number | paren addExp
     * </pre>
     */
    private int factor(final Node root) {
        int rslt = 0;
        String tagName = root.getLocalName();
        if (tagName.equals(TokenType.IDENT.toString()) || tagName.equals(TokenType.INT.toString())) {
            NodeList children = root.getChildNodes();
            if (children.getLength() == 1) {
                Node child = children.item(0);
                if (child.getNodeType() == Node.TEXT_NODE) {
                    Text textNode = (Text) child;
                    String textData = textNode.getData();
                    if (tagName.equals(TokenType.IDENT.toString())) {
                        rslt = this.symbolLookup(textData);
                    } else {
                        try {
                            rslt = Integer.parseInt(textData);
                        } catch (NumberFormatException e) {
                            logger.info("factor: bad format for number \"" + textData + "\"");
                        }
                    }
                } else {
                    logger.info("factor: unexpected node type = " + this.nodeTypeToString(child.getNodeType()));
                }
            } else {
                logger.info("factor: 1 child expected for " + tagName + ", got " + children.getLength() + " children");
            }
        } // root is not an IDENT or an INT, so it should be an expression
        else if (tagName.equals(TokenType.PAREN.toString())) {
            ArrayList<Node> children = this.toElementNodeList(root.getChildNodes());
            if (children.size() == 1) {
                Node expr = children.get(0);
                rslt = this.addExp(expr);
            } else {
                logger.info("factor: extra children of PAREN");
            }
        } else {
            logger.info("factor: Unexpected tag = " + tagName);
        }
        return rslt;
    } // factor

    /**
     * Process a factor or a unary minus expression (the silly unary plus is not supported).
     * 
     * <pre>
                unaryExp: factor | uminus factor
     * </pre>
     */
    private int unaryExp(Node root) {
        int rslt = 0;
        boolean unaryMinus = false;
        if (root.getLocalName().equals(TokenType.UMINUS.toString())) {
            ArrayList<Node> children = this.toElementNodeList(root.getChildNodes());
            if (children.size() == 1) {
                unaryMinus = true;
                root = children.get(0);
            } else {
                logger.info("unaryExp: more than one child found for UMINUS");
            }
        }
        rslt = this.factor(root);
        if (unaryMinus) {
            rslt = -rslt;
        }
        return rslt;
    } // unaryExp

    /**
     * Process a factor expression
     * 
     * <pre>
             term: factor | term mulOp term
     * </pre>
     */
    private int term(final Node root) {
        int rslt = 0;
        if (root.getLocalName().equals(TokenType.TIMES.toString()) || root.getLocalName().equals(TokenType.DIV.toString())
                || root.getLocalName().equals(TokenType.MOD.toString())) {
            ArrayList<Node> children = this.toElementNodeList(root.getChildNodes());
            Node lhs = children.get(0);
            Node rhs = children.get(1);
            int lhsInt = this.term(lhs);
            int rhsInt = this.term(rhs);
            if (root.getLocalName().equals(TokenType.TIMES.toString())) {
                rslt = lhsInt * rhsInt;
            } else if (root.getLocalName().equals(TokenType.DIV.toString())) {
                if (rhsInt != 0) { // don't allow divide by zero
                    rslt = lhsInt / rhsInt;
                }
            } else { // root.getLocalName().equals( TokenType.MOD )
                if (rhsInt != 0) { // don't allow mod by zero
                    rslt = lhsInt % rhsInt;
                }
            }
        } else {
            rslt = this.unaryExp(root);
        }
        return rslt;
    } // term

    /**
     * <pre>
               addExp: term | addExp addOp addExp
     * </pre>
     */
    private int addExp(final Node root) {
        int rslt = 0;
        if (root.getLocalName().equals(TokenType.MINUS.toString()) || root.getLocalName().equals(TokenType.PLUS.toString())) {
            ArrayList<Node> children = this.toElementNodeList(root.getChildNodes());
            Node lhs = children.get(0);
            Node rhs = children.get(1);
            int lhsInt = this.addExp(lhs);
            int rhsInt = this.addExp(rhs);
            if (root.getLocalName().equals(TokenType.MINUS.toString())) {
                rslt = lhsInt - rhsInt;
            } else {
                rslt = lhsInt + rhsInt;
            }
        } else {
            rslt = this.term(root);
        }
        return rslt;
    } // addExp

    /**
     * "Store" a value in a symbol
     */
    private void store(final Node lhs, final int value) {
        NodeList children = lhs.getChildNodes();
        Node child = children.item(0);
        if (child.getNodeType() == Node.TEXT_NODE) {
            Text textNode = (Text) child;
            String symbolName = textNode.getData();
            this.symbolEnter(symbolName, value);
        }
    } // store

    /**
     * Process and assignment statement or an expression
     * 
     * <pre>
              statement: assignment | addExp
     * </pre>
     */
    private Integer statement(final Node root) {
        Integer rslt = null;
        Node expr = root;
        Node lhs = null;
        if (root.getLocalName().equals(TokenType.EQUAL.toString())) {
            ArrayList<Node> children = this.toElementNodeList(root.getChildNodes());
            int len = children.size();
            if (len == 2) {
                lhs = children.get(0);
                expr = children.get(1);
            } else {
                logger.info("statement: two children expected, got " + len + " instead");
            }
        } else {
            expr = root;
        }
        int expRslt = this.addExp(expr);
        if (lhs != null) {
            this.store(lhs, expRslt);
        } else {
            rslt = new Integer(expRslt);
        }
        return rslt;
    } // statement

    /**
     * This function is passed an assignment statement or an expression, formatted in XML. If the argument is an
     * assignment statement, the right hand side (RHS) value is assigned to the variable on the left hand side (LHS). In
     * the case of an assignment statement the function will return null.
     * <p>
     * If the argument is an expression the expression will be evaluated. The function will return the result as an
     * Integer object.
     * </p>
     * 
     */
    public Integer eval(final DOMParser parser, final byte[] xmlBytes) {
        Integer result = null;
        Document doc = null;
        try {
            doc = bytesToDocument(parser, xmlBytes);
            Node root = doc.getDocumentElement();
            if (root.getLocalName().equals("EXPRESSION")) {
                ArrayList<Node> children = this.toElementNodeList(root.getChildNodes());
                root = children.get(0);
                result = this.statement(root);
            } else {
                logger.info("eval: EXPRESSION XML Document expected");
            }
        } catch (SAXException e) {
            String msg = null;
            if (e instanceof SAXParseException) {
                int lineNum = ((SAXParseException) e).getLineNumber();
                int columnNumber = ((SAXParseException) e).getColumnNumber();
                String exceptionMsg = ((SAXParseException) e).getMessage();
                msg = "Error: line = " + lineNum + ", column = " + columnNumber + ": " + exceptionMsg;
            } else {
                msg = "TestXerces.bytesToDocument: SAX Exception = " + e;
            }
            logger.info(msg);
        } catch (IOException e) {
            logger.info("TestXerces.bytesToDocument: IOException = " + e);
        }
        return result;
    } // eval
}
