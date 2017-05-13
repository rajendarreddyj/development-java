package com.rajendarreddyj.basics.multithreading;

import java.util.logging.Logger;

/**
 * @author rajendarreddy
 *
 */
public class RunnableDemo {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        Thread thread1 = new Thread(new RunnableThread(), "thread1");
        Thread thread2 = new Thread(new RunnableThread(), "thread2");
        new RunnableThread("thread3");
        // Start the threads
        thread1.start();
        thread2.start();
        try {
            Thread.currentThread();
            // delay for one second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        // Display info about the main thread
        logger.info(Thread.currentThread().toString());
    }

}

class RunnableThread implements Runnable {
    private static final Logger logger = Logger.getAnonymousLogger();
    Thread runner;

    public RunnableThread() {
    }

    public RunnableThread(final String threadName) {
        this.runner = new Thread(this, threadName); // (1) Create a new thread.
        logger.info(this.runner.getName().toString());
        this.runner.start(); // (2) Start the thread.
    }

    @Override
    public void run() {
        // Display info about this particular thread
        logger.info(Thread.currentThread().toString());
    }
}