package com.rajendarreddyj.basics.formatters;

import java.util.logging.Logger;

/**
 * @rajendarreddy rajendarreddy
 *
 */
public class PrintFormat {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        int a = 1, b = 2, c = 3, d = 4;
        System.out.print(a + "\t" + b);
        logger.info(b + "\n" + b);
        System.out.print(":" + c);
        logger.info("\n");// this throws cursor to the next line
        logger.info("Hello\\Hi\"" + d);

    }

}
