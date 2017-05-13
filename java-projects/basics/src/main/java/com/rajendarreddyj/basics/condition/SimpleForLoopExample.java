package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class SimpleForLoopExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        int count = 5;
        for (int index = 0; index < count; index++) {
            logger.info("Index is now : " + index);
        }
    }
}
