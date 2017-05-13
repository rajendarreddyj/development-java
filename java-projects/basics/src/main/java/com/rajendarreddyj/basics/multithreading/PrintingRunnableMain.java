package com.rajendarreddyj.basics.multithreading;

import java.util.logging.Logger;

/**
 * @author rajendarreddy
 *
 */
public class PrintingRunnableMain {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) throws InterruptedException {
        logger.info("main() started");
        Thread thread = new Thread(new PrintingRunnable(1));
        thread.start();
        logger.info("main() is waiting");
        thread.join();
        logger.info("main() finished");

    }
}
