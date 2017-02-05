package com.rajendarreddyj.basics.collections;

import java.util.Iterator;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author rajendarreddy
 *
 */
public class ConcurrentNavigableMapTests {
    @Test
    public void givenSkipListMap_whenAccessInMultiThreads_thenOrderingStable() throws InterruptedException {
        NavigableMap<Integer, String> skipListMap = new ConcurrentSkipListMap<>();
        this.updateMapConcurrently(skipListMap, 4);
        Iterator<Integer> skipListIter = skipListMap.keySet().iterator();
        int previous = skipListIter.next();
        while (skipListIter.hasNext()) {
            int current = skipListIter.next();
            Assert.assertTrue(previous < current);
        }
    }

    private void updateMapConcurrently(final NavigableMap<Integer, String> navigableMap, final int concurrencyLevel) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(concurrencyLevel);
        for (int i = 0; i < concurrencyLevel; i++) {
            executorService.execute(() -> {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                for (int j = 0; j < 10000; j++) {
                    navigableMap.put(random.nextInt(), "test");
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }

    @Test
    public void givenSkipListMap_whenNavConcurrently_thenCountCorrect() throws InterruptedException {
        NavigableMap<Integer, Integer> skipListMap = new ConcurrentSkipListMap<>();
        int count = this.countMapElementByPollingFirstEntry(skipListMap, 10000, 4);
        Assert.assertEquals(10000 * 4, count);
    }

    @Test
    public void givenTreeMap_whenNavConcurrently_thenCountError() throws InterruptedException {
        NavigableMap<Integer, Integer> treeMap = new TreeMap<>();
        int count = this.countMapElementByPollingFirstEntry(treeMap, 10000, 4);
        Assert.assertNotEquals(10000 * 4, count);
    }

    private int countMapElementByPollingFirstEntry(final NavigableMap<Integer, Integer> navigableMap, final int elementCount, final int concurrencyLevel)
            throws InterruptedException {
        for (int i = 0; i < (elementCount * concurrencyLevel); i++) {
            navigableMap.put(i, i);
        }
        AtomicInteger counter = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(concurrencyLevel);
        for (int j = 0; j < concurrencyLevel; j++) {
            executorService.execute(() -> {
                for (int i = 0; i < elementCount; i++) {
                    if (navigableMap.pollFirstEntry() != null) {
                        counter.incrementAndGet();
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        return counter.get();
    }
}
