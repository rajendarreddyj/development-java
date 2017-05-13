package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class BreakDemo {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        for (int i = 1; i < 100; i++) {
            if ((i % 13) == 0) {
                if (i == 65) {
                    continue;
                }
                logger.info(String.valueOf(i));
            }
        }
        logger.info("After the loop");
    }
}
