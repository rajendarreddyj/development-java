package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class LabeledContinue {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        first: for (int i = 2; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (i == 7) {
                    // continue first;
                    break first;
                }
                logger.info(i + "*" + j + "=" + (i * j));
            }
        }
    }
}
