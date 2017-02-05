package com.rajendarreddyj.basics.concurrent.recursivetask;

import java.util.concurrent.RecursiveTask;

/**
 * @author rajendarreddy
 *
 */
public class FactorialSquareCalculator extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    final private Integer n;

    public FactorialSquareCalculator(final Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (this.n <= 1) {
            return this.n;
        }
        FactorialSquareCalculator calculator = new FactorialSquareCalculator(this.n - 1);
        calculator.fork();
        return (this.n * this.n) + calculator.join();
    }
}
