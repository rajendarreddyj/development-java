package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class CompareIntegers {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        int i1 = 12;
        int i2 = 43;
        if (i1 > i2) {
            logger.info(i1 + " is greater than " + i2);
        } else if (i1 < i2) {
            logger.info(i1 + " is less than " + i2);
        } else {
            logger.info(i1 + " is equal to " + i2);
        }
    }
}
