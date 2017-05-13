package com.rajendarreddyj.basics.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Logger;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import com.rajendarreddyj.basics.enums.Token;
import com.rajendarreddyj.basics.enums.TokenType;

/**
 * ParseExpToXML
 *
 * <p>
 * The syntax for assignments and expressions in the input string is:
 * </p>
 * 
 * <pre>
        statement: assignment | addExp

        assignment: ident "=" addExp

        addExp: term | term addOp addExp

        term: unaryExpr | unaryExpr mulOp term

        unaryExpr: factor | minus factor

        factor: ident | number | "(" addExp ")"
 * </pre>
 *
 * 
 * 
 */
@SuppressWarnings("deprecation")
public class ParseExpToXML {
    private static final Logger logger = Logger.getAnonymousLogger();
    private Document mDoc = null;
    private Scanner mScan = null;
    private String mNameSpace = null;
    private String mPrefix = null;

    /**
     * Create an DOM Element object. If mNameSpace is set then create the Element with a name space, otherwise, create
     * an unqualified Element.
     */
    private Element createElement(String xmlTag) {
        Element elem = null;
        if (this.mNameSpace == null) {
            elem = this.mDoc.createElement(xmlTag);
        } else {
            if (this.mPrefix != null) {
                xmlTag = this.mPrefix + ":" + xmlTag;
            }
            elem = this.mDoc.createElementNS(this.mNameSpace, xmlTag);
        }
        return elem;
    } // createElement

    /* 
       This function is passed a DOM document and returns the 
       XML (in String form) for this document.  The XML is produced
       with indentation (for readability in debugging).
    */
    private String documentToString(final Document doc) {
        StringWriter writer = new StringWriter();
        OutputFormat out = new OutputFormat();
        out.setOmitXMLDeclaration(true);
        out.setIndenting(true);
        out.setIndent(4);
        out.setLineSeparator(System.getProperty("line.separator"));
        out.setLineWidth(Integer.MAX_VALUE);
        XMLSerializer serializer = new XMLSerializer(writer, out);
        try {
            Element rootElement = doc.getDocumentElement();
            serializer.serialize(rootElement);
        } catch (IOException e) {
            logger.info("ParseExpToXML::documentToString: IOException = " + e);
        }
        return writer.toString();
    } // documentToString

    /**
     * factor: ident | number | "(" expression ")"
     * 
     * @throws ExpParseException
     */
    private Node factor() throws ExpParseException {
        final String msg = "identifier, number or ( exp ) expected";
        Node fact = null;
        Token f = this.mScan.getToken();
        String errMsg = null;
        if (f != null) {
            if ((f.getType() == TokenType.IDENT) || (f.getType() == TokenType.INT)) {
                if (f.getType() == TokenType.INT) {
                    fact = this.createElement(TokenType.INT.getString());
                } else {
                    fact = this.createElement(TokenType.IDENT.getString());
                }
                Text txt = this.mDoc.createTextNode(f.getString());
                fact.appendChild(txt);
            } else if (f.getType() == TokenType.LPAREN) {
                fact = this.addExp();
                f = this.mScan.getToken();
                if ((f == null) || (f.getType() != TokenType.RPAREN)) {
                    errMsg = "\")\" expected";
                } else {
                    Node top = this.createElement(TokenType.PAREN.getString());
                    top.appendChild(fact);
                    fact = top;
                }
            } else {
                errMsg = msg;
            }
        } else {
            errMsg = msg;
        }
        if (errMsg != null) {
            throw new ExpParseException(errMsg);
        }
        return fact;
    } // factor

    /**
     * unaryExpr: factor | minus factor
     */
    private Node unaryExpr() throws ExpParseException {
        boolean unaryMinus = false;
        Token t = this.mScan.getToken();
        if (t.getType() == TokenType.MINUS) {
            unaryMinus = true;
        } else {
            this.mScan.pushToken(t);
        }
        Node top = this.factor();
        if (unaryMinus) {
            Node minus = this.createElement(TokenType.UMINUS.getString());
            minus.appendChild(top);
            top = minus;
        }
        return top;
    } // unaryExpr

    /**
     * term: unaryExpr | unaryExpr addOp term
     * 
     * @throws ExpParseException
     */
    private Node term() throws ExpParseException {
        Node exp = null;
        Node t = this.unaryExpr(); // either unaryExp or the LHS of the sub-expression
        Token op = this.mScan.getToken();
        if ((op.getType() == TokenType.TIMES) || (op.getType() == TokenType.DIV) || (op.getType() == TokenType.MOD)) {
            Node rhs = this.term();
            Node mulOp = this.createElement(op.getType().getString());
            mulOp.appendChild(t);
            mulOp.appendChild(rhs);
            exp = mulOp;
        } else {
            exp = t;
            if (op.getType() != TokenType.EOL) {
                this.mScan.pushToken(op);
            }
        }
        return exp;
    } // term

    /**
     * addExp: term | term addOp addExp
     * 
     * @throws ExpParseException
     */
    private Node addExp() throws ExpParseException {
        Node exp = null;
        Node t = this.term();
        Token op = this.mScan.getToken();
        if ((op.getType() == TokenType.MINUS) || (op.getType() == TokenType.PLUS)) {
            Node rhs = this.addExp();
            Node addOp = this.createElement(op.getType().getString());
            addOp.appendChild(t);
            addOp.appendChild(rhs);
            exp = addOp;
        } else {
            exp = t;
            if (op.getType() != TokenType.EOL) {
                this.mScan.pushToken(op);
            }
        }
        return exp;
    } // addExp

    /**
     * statement: assign | expression assign: ident "=" expression;
     * 
     * @throws ExpParseException
     */
    private Node statement() throws ExpParseException {
        String errMsg = null;
        Node stmt = null;
        Node lhs = this.addExp();
        Token t = this.mScan.getToken();
        if (this.mScan.isEOL()) {
            stmt = lhs;
        } else if (t.getType() == TokenType.EQUAL) {
            if (lhs.getLocalName().equals(TokenType.IDENT.getString())) {
                Node rhs = this.addExp();
                Node equals = this.createElement(t.getType().getString());
                equals.appendChild(lhs);
                equals.appendChild(rhs);
                stmt = equals;
                t = this.mScan.getToken();
                if (t.getType() != TokenType.EOL) {
                    errMsg = "Syntax error: badly formed expression";
                }
            } else {
                errMsg = "LHS identifier expected";
            }
        } else {
            errMsg = "\"=\" expected";
        }
        if (errMsg != null) {
            throw new ExpParseException(errMsg);
        }
        return stmt;
    } // statement

    public String parse(final String exp, final String topTag, final String prefix, final String nameSpace, final String schemaLoc) throws ExpParseException {
        this.mDoc = DocumentFactory.newDocument(topTag, prefix, nameSpace, schemaLoc);
        this.mPrefix = prefix;
        this.mNameSpace = nameSpace;
        this.mScan = new Scanner(exp);
        Node root = this.mDoc.getDocumentElement();
        Node child = this.statement();
        if (child != null) {
            root.appendChild(child);
        }
        String xml = this.documentToString(this.mDoc);
        return xml;
    } // parse
}
