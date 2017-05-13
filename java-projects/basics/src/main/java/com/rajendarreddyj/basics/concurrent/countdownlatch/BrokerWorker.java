package com.rajendarreddyj.basics.concurrent.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

/**
 * @author rajendarreddy
 *
 */
public class BrokerWorker implements Runnable {
    private static final Logger logger = Logger.getAnonymousLogger();
    private final List<String> outputScraper;
    private final CountDownLatch countDownLatch;

    public BrokerWorker(final List<String> outputScraper, final CountDownLatch countDownLatch) {
        this.outputScraper = outputScraper;
        this.countDownLatch = countDownLatch;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // Do some work
        logger.info("Doing some logic");
        this.outputScraper.add("Counted down");
        this.countDownLatch.countDown();
    }
}
