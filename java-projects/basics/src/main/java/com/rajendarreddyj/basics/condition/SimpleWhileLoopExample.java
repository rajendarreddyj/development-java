package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class SimpleWhileLoopExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        int i = 0;
        int max = 5;
        logger.info("Counting to " + max);
        while (i < max) {
            i++;
            logger.info("i is : " + i);
        }
    }
}
