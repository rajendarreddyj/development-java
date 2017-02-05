package com.rajendarreddyj.basics.enums;

/**
 * TokenType
 *
 *
 */
public class TokenType extends TypeSafeEnum {
    public TokenType(final String name) {
        super(name, TokenType.class);
    }

    public String getString() {
        return this.toString();
    }

    public static TokenType DIV = new TokenType("DIV");
    public static TokenType EOL = new TokenType("EOL");
    public static TokenType EQUAL = new TokenType("EQUAL");
    public static TokenType IDENT = new TokenType("IDENT");
    public static TokenType INT = new TokenType("INT");
    public static TokenType MINUS = new TokenType("MINUS");
    public static TokenType UMINUS = new TokenType("UMINUS");
    public static TokenType MOD = new TokenType("MOD");
    public static TokenType NULL_TYPE = new TokenType("NULL_TYPE");
    public static TokenType LPAREN = new TokenType("LPAREN");
    public static TokenType RPAREN = new TokenType("RPAREN");
    public static TokenType PAREN = new TokenType("PAREN");
    public static TokenType PLUS = new TokenType("PLUS");
    public static TokenType TIMES = new TokenType("TIMES");
}
