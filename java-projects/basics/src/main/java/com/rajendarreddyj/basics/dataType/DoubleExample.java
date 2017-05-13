package com.rajendarreddyj.basics.dataType;

import java.util.logging.Logger;

public class DoubleExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        double d1 = 5445.27;
        double d2 = (double) 1234 / 33;
        logger.info("Value of double d1 is: " + d1);
        logger.info("Value of double d2 is: " + d2);
    }
}
