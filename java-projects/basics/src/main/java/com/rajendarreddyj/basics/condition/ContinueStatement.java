package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class ContinueStatement {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        int array[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        logger.info("Printing all odd numbers");
        for (int i = 0; i < array.length; i++) {
            if ((array[i] % 2) == 0) {
                continue;
            } else {
                System.out.print(array[i] + " ");
            }
        }
    }
}
