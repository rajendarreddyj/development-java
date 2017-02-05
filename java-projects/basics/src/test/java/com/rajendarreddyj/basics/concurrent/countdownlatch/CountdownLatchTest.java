package com.rajendarreddyj.basics.concurrent.countdownlatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @author rajendarreddy
 *
 */
public class CountdownLatchTest {
    @Test
    public void whenParallelProcessing_thenMainThreadWillBlockUntilCompletion() throws InterruptedException {
        // Given
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new Worker(outputScraper, countDownLatch))).limit(5).collect(Collectors.toList());
        // When
        workers.forEach(Thread::start);
        countDownLatch.await(); // Block until workers finish
        outputScraper.add("Latch released");
        // Then
        outputScraper.forEach(Object::toString);
        Assertions.assertThat(outputScraper).containsExactly("Counted down", "Counted down", "Counted down", "Counted down", "Counted down", "Latch released");
    }

    @Test
    public void whenFailingToParallelProcess_thenMainThreadShouldTimeout() throws InterruptedException {
        // Given
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new BrokerWorker(outputScraper, countDownLatch))).limit(5).collect(Collectors.toList());
        // When
        workers.forEach(Thread::start);
        final boolean result = countDownLatch.await(3L, TimeUnit.SECONDS);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void whenDoingLotsOfThreadsInParallel_thenStartThemAtTheSameTime() throws InterruptedException {
        // Given
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch readyThreadCounter = new CountDownLatch(5);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(5);
        List<Thread> workers = Stream
                .generate(() -> new Thread(new WaitingWorker(outputScraper, readyThreadCounter, callingThreadBlocker, completedThreadCounter))).limit(5)
                .collect(Collectors.toList());
        // When
        workers.forEach(Thread::start);
        readyThreadCounter.await(); // Block until workers start
        outputScraper.add("Workers ready");
        callingThreadBlocker.countDown(); // Start workers
        completedThreadCounter.await(); // Block until workers finish
        outputScraper.add("Workers complete");
        // Then
        outputScraper.forEach(Object::toString);
        Assertions.assertThat(outputScraper).containsExactly("Workers ready", "Counted down", "Counted down", "Counted down", "Counted down", "Counted down",
                "Workers complete");
    }
}
