package com.rajendarreddyj.basics.xml;

import com.rajendarreddyj.basics.enums.Token;
import com.rajendarreddyj.basics.enums.TokenType;

/**
 * TestScanner
 * 
 * A test object
 * <p>
 * Read an expression (in quotes: "3 * 4 + 5") from the command line and call the Scanner.getToken() method to tokenize
 * the string.
 * </p>
 * 
 */
public class TestScanner {
    private void test(final String argv[]) {
        if (argv.length == 1) {
            String expr = argv[0];
            System.out.println("expr = " + expr);
            Scanner scan = new Scanner(expr);
            for (Token tok = scan.getToken(); tok.getType() != TokenType.EOL; tok = scan.getToken()) {
                System.out.println("token = " + tok);
            }
        } else {
            System.out.println("test expression on the command line expected");
        }
    }

    public static void main(final String[] argv) {
        TestScanner t = new TestScanner();
        t.test(argv);
    }
}
