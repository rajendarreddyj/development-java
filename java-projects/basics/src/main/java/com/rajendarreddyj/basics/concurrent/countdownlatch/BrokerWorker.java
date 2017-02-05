package com.rajendarreddyj.basics.concurrent.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author rajendarreddy
 *
 */
public class BrokerWorker implements Runnable {
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
        System.out.println("Doing some logic");
        this.outputScraper.add("Counted down");
        this.countDownLatch.countDown();
    }
}
