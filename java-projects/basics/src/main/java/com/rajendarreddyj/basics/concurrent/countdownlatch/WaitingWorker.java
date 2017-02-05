package com.rajendarreddyj.basics.concurrent.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author rajendarreddy
 *
 */
public class WaitingWorker implements Runnable {
    private final List<String> outputScraper;
    private final CountDownLatch readyThreadCounter;
    private final CountDownLatch callingThreadBlocker;
    private final CountDownLatch completedThreadCounter;

    public WaitingWorker(final List<String> outputScraper, final CountDownLatch readyThreadCounter, final CountDownLatch callingThreadBlocker,
            final CountDownLatch completedThreadCounter) {
        this.outputScraper = outputScraper;
        this.readyThreadCounter = readyThreadCounter;
        this.callingThreadBlocker = callingThreadBlocker;
        this.completedThreadCounter = completedThreadCounter;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // Mark this thread as read / started
        this.readyThreadCounter.countDown();
        try {
            this.callingThreadBlocker.await();
            this.outputScraper.add("Counted down");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.completedThreadCounter.countDown();
        }
    }
}
