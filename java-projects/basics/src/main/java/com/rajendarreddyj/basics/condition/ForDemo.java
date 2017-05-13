package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class ForDemo {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        for (int i = 0; i < 100; i++) {
            logger.info(String.valueOf(i));
        }
    }
}
