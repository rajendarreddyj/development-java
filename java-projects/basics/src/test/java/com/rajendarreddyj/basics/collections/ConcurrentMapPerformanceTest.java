package com.rajendarreddyj.basics.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author rajendarreddy
 *
 */
public class ConcurrentMapPerformanceTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    @Test
    public void givenMaps_whenGetPut500KTimes_thenConcurrentMapFaster() throws Exception {
        Map<String, Object> hashtable = new Hashtable<>();
        Map<String, Object> synchronizedHashMap = Collections.synchronizedMap(new HashMap<>());
        Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        long hashtableAvgRuntime = this.timeElapseForGetPut(hashtable);
        long syncHashMapAvgRuntime = this.timeElapseForGetPut(synchronizedHashMap);
        long concurrentHashMapAvgRuntime = this.timeElapseForGetPut(concurrentHashMap);
        logger.info(String.format("Hashtable: %s, syncHashMap: %s, ConcurrentHashMap: %s", hashtableAvgRuntime, syncHashMapAvgRuntime,
                concurrentHashMapAvgRuntime));
        Assert.assertTrue(hashtableAvgRuntime > concurrentHashMapAvgRuntime);
        Assert.assertTrue(syncHashMapAvgRuntime > concurrentHashMapAvgRuntime);
    }

    private long timeElapseForGetPut(final Map<String, Object> map) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        long startTime = System.nanoTime();
        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 500_000; j++) {
                    int value = ThreadLocalRandom.current().nextInt(10000);
                    String key = String.valueOf(value);
                    map.put(key, value);
                    map.get(key);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        return (System.nanoTime() - startTime) / 500_000;
    }

    @Test
    public void givenConcurrentMap_whenKeyWithSameHashCode_thenPerformanceDegrades() throws InterruptedException {
        class SameHash {
            @Override
            public int hashCode() {
                return 1;
            }
        }
        int executeTimes = 5000;
        Map<SameHash, Integer> mapOfSameHash = new ConcurrentHashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        long sameHashStartTime = System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < executeTimes; j++) {
                    mapOfSameHash.put(new SameHash(), 1);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        long mapOfSameHashDuration = System.currentTimeMillis() - sameHashStartTime;
        Map<Object, Integer> mapOfDefaultHash = new ConcurrentHashMap<>();
        executorService = Executors.newFixedThreadPool(2);
        long defaultHashStartTime = System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < executeTimes; j++) {
                    mapOfDefaultHash.put(new Object(), 1);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        long mapOfDefaultHashDuration = System.currentTimeMillis() - defaultHashStartTime;
        Assert.assertEquals(executeTimes * 2, mapOfDefaultHash.size());
        Assert.assertEquals(executeTimes * 2, mapOfSameHash.size());
        logger.info(String.format("same-hash: %s, default-hash: %s", mapOfSameHashDuration, mapOfDefaultHashDuration));
        Assert.assertTrue("same hashCode() should greatly degrade performance", mapOfSameHashDuration > (mapOfDefaultHashDuration * 10));
    }
}
