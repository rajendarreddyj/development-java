package com.rajendarreddyj.basics.multithreading;

import java.util.logging.Logger;

/**
 * @author rajendarreddy
 *
 */
public class FailingRunnableMain {
    private static final Logger logger = Logger.getAnonymousLogger();
    /**
     * @param args
     */
    public static void main(final String[] args) {
        Thread thread = new Thread(new FailingRunnable());
        thread.start();
        logger.info("main() finished");
    }

}
