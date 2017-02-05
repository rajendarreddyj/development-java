package com.rajendarreddyj.basics.multithreading;

/**
 * This counter can be incremented and decremented. We synchronized the mutator methods to make sure that multiple
 * threads can't be changing the value at the same time. This makes the class thread safe.
 * 
 * @author rajendarreddy
 *
 */
public class Counter {
    private int c;

    public Counter() {
        this.c = 0;
    }

    public synchronized void increment() {
        int old = this.c;
        this.c = old + 1; // c++;
    }

    public synchronized void decrement() {
        int old = this.c;
        this.c = old - 1; // c--;
    }

    public int value() {
        return this.c;
    }
}
