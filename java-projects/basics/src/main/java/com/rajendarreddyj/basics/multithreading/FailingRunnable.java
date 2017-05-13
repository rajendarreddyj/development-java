package com.rajendarreddyj.basics.multithreading;

import java.util.logging.Logger;

/**
 * @author rajendarreddy
 *
 */
public class FailingRunnable implements Runnable {
    private static final Logger logger = Logger.getAnonymousLogger();
    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @SuppressWarnings("unused")
    @Override
    public void run() {
        logger.info("Thread started");

        // The compiler can detect when some code cannot be reached
        // so this "if" statement is used to trick the compiler
        // into letting me write a println after throwing
        if (true) {
            throw new RuntimeException("Stopping the thread");
        }
        logger.info("This won't be printed");
    }

}
