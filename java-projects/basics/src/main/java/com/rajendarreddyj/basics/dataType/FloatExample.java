package com.rajendarreddyj.basics.dataType;

import java.util.logging.Logger;

public class FloatExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        float f1 = 422.27f;
        float f2 = (float) 1234 / 33;
        logger.info("Value of float f1 is: " + f1);
        logger.info("Value of float f2 is: " + f2);
    }
}
