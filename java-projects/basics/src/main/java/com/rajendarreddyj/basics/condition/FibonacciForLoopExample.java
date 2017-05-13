package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class FibonacciForLoopExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        int max = 10;
        long[] series = new long[max];
        // create first 2 numbers
        series[0] = 0;
        series[1] = 1;
        // calculate the Fibonacci numbers and store them
        for (int i = 2; i < max; i++) {
            series[i] = series[i - 1] + series[i - 2];
        }
        logger.info("Fibonacci Series for " + max);
        for (int i = 0; i < max; i++) {
            System.out.print(series[i] + " ");
        }
    }
}
