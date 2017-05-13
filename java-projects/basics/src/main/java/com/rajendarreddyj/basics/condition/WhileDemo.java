package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class WhileDemo {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        int x = 0;
        while (x < 20) {
            logger.info(String.valueOf(x));
            x++;
        }
        logger.info("End of loop");
    }
}
