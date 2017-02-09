package com.rajendarreddyj.basics.multithreading;

/**
 * @author rajendarreddy
 *
 */
public class PrintingRunnable implements Runnable {
    private final int id;

    public PrintingRunnable(final int id) {
        this.id = id;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            // Print a message five times
            for (int i = 0; i < 3; i++) {
                System.out.println("Message " + i + " from Thread " + this.id);
                // Wait for half a second (500ms)
                Thread.sleep(500);
            }
        } catch (InterruptedException ex) {
            System.out.println("Thread was interrupted");
        }

    }

}
