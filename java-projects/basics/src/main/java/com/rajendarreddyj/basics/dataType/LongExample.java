package com.rajendarreddyj.basics.dataType;

import java.util.logging.Logger;

public class LongExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        long l1 = System.currentTimeMillis();
        long l2 = 1000 * 1000;
        logger.info("Value of long l1 is: " + l1);
        logger.info("Value of long l2 is: " + l2);
    }
}
