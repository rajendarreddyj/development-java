package com.rajendarreddyj.basics.condition;

public class FibonacciForLoopExample {
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
        System.out.println("Fibonacci Series for " + max);
        for (int i = 0; i < max; i++) {
            System.out.print(series[i] + " ");
        }
    }
}
