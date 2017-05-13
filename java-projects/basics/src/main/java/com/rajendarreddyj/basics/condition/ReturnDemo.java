package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class ReturnDemo {
    private static final Logger logger = Logger.getAnonymousLogger();
    public void multiples(final int x) {
        for (int i = 1; i <= 10; i++) {
            if (i == 9) {
                return;
            }
            logger.info(String.valueOf(x * i));
        }
    }

    public static void main(final String[] args) {
        logger.info("Before calling the Multiples");
        ReturnDemo returnDemo = new ReturnDemo();
        returnDemo.multiples(5);
        logger.info("Multiples Method finished");
    }
}
