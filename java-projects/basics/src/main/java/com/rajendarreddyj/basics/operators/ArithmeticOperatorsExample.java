package com.rajendarreddyj.basics.operators;

import java.util.logging.Logger;

public class ArithmeticOperatorsExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        int i = 30 + 20;
        int j = i - 5;
        int k = j * 2;
        double l = k / 6;
        logger.info("i = " + i);
        logger.info("j = " + j);
        logger.info("k = " + k);
        logger.info("l = " + l);
    }
}
