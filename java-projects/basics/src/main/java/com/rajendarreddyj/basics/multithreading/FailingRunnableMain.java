package com.rajendarreddyj.basics.multithreading;

/**
 * @author rajendarreddy
 *
 */
public class FailingRunnableMain {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        Thread thread = new Thread(new FailingRunnable());
        thread.start();
        System.out.println("main() finished");
    }

}
