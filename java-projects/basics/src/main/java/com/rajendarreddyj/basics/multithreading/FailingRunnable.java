package com.rajendarreddyj.basics.multithreading;

/**
 * @author rajendarreddy
 *
 */
public class FailingRunnable implements Runnable {

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @SuppressWarnings("unused")
    @Override
    public void run() {
        System.out.println("Thread started");

        // The compiler can detect when some code cannot be reached
        // so this "if" statement is used to trick the compiler
        // into letting me write a println after throwing
        if (true) {
            throw new RuntimeException("Stopping the thread");
        }
        System.out.println("This won't be printed");
    }

}
