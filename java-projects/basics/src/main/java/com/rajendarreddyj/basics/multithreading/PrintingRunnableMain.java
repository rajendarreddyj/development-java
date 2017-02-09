package com.rajendarreddyj.basics.multithreading;

/**
 * @author rajendarreddy
 *
 */
public class PrintingRunnableMain {

    public static void main(final String[] args) throws InterruptedException {
        System.out.println("main() started");
        Thread thread = new Thread(new PrintingRunnable(1));
        thread.start();
        System.out.println("main() is waiting");
        thread.join();
        System.out.println("main() finished");

    }
}
