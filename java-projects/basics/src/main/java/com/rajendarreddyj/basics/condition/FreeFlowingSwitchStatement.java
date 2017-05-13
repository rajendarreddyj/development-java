package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class FreeFlowingSwitchStatement {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        int i = 0;
        // break statement is not specified, thus switch statement becomes free
        // flowing
        // all cases after the matching one (including the default) will be
        // executed
        switch (i) {
        case 0:
            logger.info("i is 0");
        case 1:
            logger.info("i is 1");
        case 2:
            logger.info("i is 2");
        default:
            logger.info("Free flowing switch");
        }
    }
}
