package com.rajendarreddyj.basics.concurrent.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author rajendarreddy
 *
 */
public class SquareCalculator {
    private final ExecutorService executor;

    public SquareCalculator(final ExecutorService executor) {
        this.executor = executor;
    }

    public Future<Integer> calculate(final Integer input) {
        return this.executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }
}
