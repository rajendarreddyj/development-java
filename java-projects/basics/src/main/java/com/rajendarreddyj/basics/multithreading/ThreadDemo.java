package com.rajendarreddyj.basics.multithreading;

/**
 * @author rajendarreddy
 *
 */
public class ThreadDemo {
    public static void main(final String[] args) {
        Thread thread1 = new Thread(new XThread(), "thread1");
        Thread thread2 = new Thread(new XThread(), "thread2");
        // The below 2 threads are assigned default names
        Thread thread3 = new XThread();
        Thread thread4 = new XThread();
        new XThread("thread5");
        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            Thread.currentThread();
            // The sleep() method is invoked on the main thread to cause a one second delay.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        // Display info about the main thread
        System.out.println(Thread.currentThread());
    }
}

class XThread extends Thread {

    XThread() {
    }

    XThread(final String threadName) {
        super(threadName); // Initialize thread.
        System.out.println(this);
        this.start();
    }

    @Override
    public void run() {
        // Display info about this particular thread
        System.out.println(Thread.currentThread().getName());
    }
}
