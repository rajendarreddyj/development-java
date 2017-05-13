package com.rajendarreddyj.basics.dataType;

import java.util.logging.Logger;

public class BooleanExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        boolean b1 = true;
        boolean b2 = false;
        boolean b3 = (2 > 1);
        logger.info("Value of boolean b1: " + b1);
        logger.info("Value of boolean b2: " + b2);
        logger.info("Value of boolean b3: " + b3);
    }
}
