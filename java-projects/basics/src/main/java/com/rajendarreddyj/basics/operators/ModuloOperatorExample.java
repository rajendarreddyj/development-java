package com.rajendarreddyj.basics.operators;

import java.util.logging.Logger;

public class ModuloOperatorExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        int i = 81;
        double d = 48;
        logger.info(i + " mod 10 = " + (i % 10));
        logger.info(d + " mod 10 = " + (d % 10));
    }
}
