package com.rajendarreddyj.basics.xml;

import java.util.LinkedList;

import com.rajendarreddyj.basics.enums.Token;
import com.rajendarreddyj.basics.enums.TokenType;

/**
 * Scanner
 *
 * Read an expression (e.g., 3 + 4 * x) and return a set of tokens that represent the components of the expression.
 *
 * 
 */
public class Scanner {
    private String mExp = null;
    private int mExpLen = 0;
    private boolean mEOL = false;
    private int mCursor = 0;
    private char[] mCharBuf = new char[80];
    private int mBufIx = 0;
    /** A First In, First Out list for tokens which have been "pushed back" */
    private LinkedList<Token> mPushList = new LinkedList<>();

    /**
     * Add a character to the array that contains the token string
     */
    private void putChar(final char ch) {
        this.mCharBuf[this.mBufIx] = ch;
        this.mBufIx++;
    }

    /**
     * Copy a string of identifier characters into the token character array
     */
    private void getIdent(char ch) {
        while ((this.mCursor < this.mExpLen) && Character.isLetterOrDigit(ch)) {
            this.putChar(ch);
            this.mCursor++;
            if (this.mCursor < this.mExpLen) {
                ch = this.mExp.charAt(this.mCursor);
            }
        }
        this.mCursor--;
    } // getIdent

    /**
     * Copy a string of numbers into the token character array.
     */
    private void getInt(char ch) {
        while ((this.mCursor < this.mExpLen) && Character.isDigit(ch)) {
            this.putChar(ch);
            this.mCursor++;
            if (this.mCursor < this.mExpLen) {
                ch = this.mExp.charAt(this.mCursor);
            }
        }
        this.mCursor--;
    } // getInt

    /**
     * Skip white space characters
     */
    private void skipSpaces() {
        while ((this.mCursor < this.mExpLen) && Character.isWhitespace(this.mExp.charAt(this.mCursor))) {
            this.mCursor++;
        }
    } // skipSpaces

    /**
     * The lexer method does simple "lexical analysis", which divides the character stream into a set of tokens.
     */
    private TokenType lexer() {
        TokenType type = TokenType.NULL_TYPE;
        this.skipSpaces();
        this.mBufIx = 0;
        if (this.mCursor < this.mExpLen) {
            char ch = this.mExp.charAt(this.mCursor);
            if (Character.isLetter(ch)) {
                this.getIdent(ch);
                type = TokenType.IDENT;
            } else if (Character.isDigit(ch)) {
                this.getInt(ch);
                type = TokenType.INT;
            } else if (ch == '=') {
                type = TokenType.EQUAL;
            } else if (ch == '+') {
                type = TokenType.PLUS;
            } else if (ch == '-') {
                type = TokenType.MINUS;
            } else if (ch == '*') {
                type = TokenType.TIMES;
            } else if (ch == '/') {
                type = TokenType.DIV;
            } else if (ch == '%') {
                type = TokenType.MOD;
            } else if (ch == '(') {
                type = TokenType.LPAREN;
            } else if (ch == ')') {
                type = TokenType.RPAREN;
            }
            this.mCursor++;
        } else {
            this.mEOL = true;
            type = TokenType.EOL;
        }
        return type;
    } // lexer

    public Scanner(final String exp) {
        this.mExp = exp;
        this.mExpLen = exp.length();
    } // Scanner

    /**
     * Return the next expression token
     */
    public Token getToken() {
        Token tok = null;
        if (this.mPushList.size() == 0) {
            TokenType type = null;
            if (this.isEOL()) {
                type = TokenType.EOL;
            } else {
                type = this.lexer();
            }
            tok = new Token(type);
            if ((type == TokenType.IDENT) || (type == TokenType.INT)) {
                String str = new String(this.mCharBuf, 0, this.mBufIx);
                tok.setString(str);
            }
        } else {
            tok = this.mPushList.removeFirst();
        }
        return tok;
    } // getToken

    /**
     * "Push" a token back into the token stream. If a token is pushed back and then getToken() is called, the pushed
     * token will the the token returned by getToken().
     */
    public void pushToken(final Token t) {
        this.mPushList.add(t);
    } // pushToken

    /**
     * @return true, if the scanner has reached the end of the line (expression)
     */
    public boolean isEOL() {
        return this.mEOL;
    } // isEOL
}
