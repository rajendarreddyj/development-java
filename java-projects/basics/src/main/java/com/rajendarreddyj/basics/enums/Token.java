package com.rajendarreddyj.basics.enums;

/**
 * Token
 *
 * Token objects are returned by the Scanner getToken() method. They are used by ParseExpToXML to build an XML string
 * from an expression.
 *
 * 
 */
public class Token {
    private TokenType mType = null;
    private String mString = null;

    public Token(final TokenType ty) {
        this.mType = ty;
    }

    public TokenType getType() {
        return this.mType;
    }

    public void setString(final String s) {
        this.mString = s;
    }

    public String getString() {
        return this.mString;
    }

    @Override
    public String toString() {
        String str = null;
        if ((this.mType == TokenType.IDENT) || (this.mType == TokenType.INT)) {
            str = this.mString;
        } else {
            if (this.mType != null) {
                str = this.mType.toString();
            }
        }
        return str;
    } // toString
}
