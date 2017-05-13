package com.rajendarreddyj.basics.concurrent.priorityblockingqueue;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author rajendarreddy
 *
 */
public class PriorityBlockingQueueTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    @Test
    public void givenUnorderedValues_whenPolling_thenShouldOrderQueue() throws InterruptedException {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        ArrayList<Integer> polledElements = new ArrayList<>();
        queue.add(1);
        queue.add(5);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.drainTo(polledElements);
        Assertions.assertThat(polledElements).containsExactly(1, 2, 3, 4, 5);
    }

    @Test
    public void whenPollingEmptyQueue_thenShouldBlockThread() throws InterruptedException {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        final Thread thread = new Thread(() -> {
            logger.info("Polling...");
            while (true) {
                try {
                    Integer poll = queue.take();
                    logger.info("Polled: " + poll);
                } catch (InterruptedException e) {
                }
            }
        });
        thread.start();
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        logger.info("Adding to queue");
        queue.addAll(Lists.newArrayList(1, 5, 6, 1, 2, 6, 7));
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
    }
}
