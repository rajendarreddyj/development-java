package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class SumArrayWithForLoop {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        // array to sum
        int[] numbers = new int[] { 10, 10, 10, 10 };
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum = sum + numbers[i];
        }
        logger.info("Sum value of array elements is : " + sum);
    }
}
