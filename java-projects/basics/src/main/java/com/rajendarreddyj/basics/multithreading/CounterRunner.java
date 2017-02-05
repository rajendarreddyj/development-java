package com.rajendarreddyj.basics.multithreading;

import java.util.Random;

/**
 * A Runnable task to increment or decrement a Counter a given number of times. Many of these will be created in
 * threads.
 * 
 * @author rajendarreddy
 *
 */
public class CounterRunner implements Runnable {
    private static final Random RANDOM = new Random(42);
    private Counter counter;
    private int times;
    private boolean increment;

    public CounterRunner(final Counter counter, final int times, final boolean increment) {
        this.counter = counter;
        this.times = times;
        this.increment = increment;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.times; i++) {
            try {
                Thread.sleep(10 + RANDOM.nextInt(50));
            } catch (InterruptedException ie) {
            }
            if (this.increment) {
                this.counter.increment();
            } else {
                this.counter.decrement();
            }
        }
    }
}