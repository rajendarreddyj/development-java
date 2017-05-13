package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class IfElseDemo {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        int x = 4;
        if (x > 5) {
            logger.info("x is greater than 5");
        } else if (x > 3) {
            logger.info("x is greater than 3");
        } else {
            logger.info("x is not greater than 5 and 3");
        }
    }
}
