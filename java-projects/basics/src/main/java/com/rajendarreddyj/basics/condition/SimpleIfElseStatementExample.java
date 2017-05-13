package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class SimpleIfElseStatementExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        boolean b = false;
        if (b) {
            logger.info("Variable value is true");
        } else {
            logger.info("Variable value is NOT true");
        }
    }
}
