package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class SimpleIfStatementExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        boolean b = true;
        if (b) {
            logger.info("Variable value is true");
        }
    }
}
