package com.rajendarreddyj.basics.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author rajendarreddy
 *
 */
public class ConcurrentMapPitfallsTest {
    private ExecutorService executorService;
    private Map<String, Integer> concurrentMap;
    private List<Integer> mapSizes;
    private int MAX_SIZE = 100000;

    @Before
    public void init() {
        this.executorService = Executors.newFixedThreadPool(2);
        this.concurrentMap = new ConcurrentHashMap<>();
        this.mapSizes = new ArrayList<>(this.MAX_SIZE);
    }

    @Test
    public void givenConcurrentMap_whenSizeWithoutConcurrentUpdates_thenCorrect() throws InterruptedException {
        Runnable collectMapSizes = () -> {
            for (int i = 0; i < this.MAX_SIZE; i++) {
                this.concurrentMap.put(String.valueOf(i), i);
                this.mapSizes.add(this.concurrentMap.size());
            }
        };
        Runnable retrieveMapData = () -> {
            for (int i = 0; i < this.MAX_SIZE; i++) {
                this.concurrentMap.get(String.valueOf(i));
            }
        };
        this.executorService.execute(retrieveMapData);
        this.executorService.execute(collectMapSizes);
        this.executorService.shutdown();
        this.executorService.awaitTermination(1, TimeUnit.MINUTES);
        for (int i = 1; i <= this.MAX_SIZE; i++) {
            Assert.assertEquals("map size should be consistently reliable", i, this.mapSizes.get(i - 1).intValue());
        }
        Assert.assertEquals(this.MAX_SIZE, this.concurrentMap.size());
    }

    @Test
    public void givenConcurrentMap_whenUpdatingAndGetSize_thenError() throws InterruptedException {
        Runnable collectMapSizes = () -> {
            for (int i = 0; i < this.MAX_SIZE; i++) {
                this.mapSizes.add(this.concurrentMap.size());
            }
        };
        Runnable updateMapData = () -> {
            for (int i = 0; i < this.MAX_SIZE; i++) {
                this.concurrentMap.put(String.valueOf(i), i);
            }
        };
        this.executorService.execute(updateMapData);
        this.executorService.execute(collectMapSizes);
        this.executorService.shutdown();
        this.executorService.awaitTermination(1, TimeUnit.MINUTES);
        Assert.assertNotEquals("map size collected with concurrent updates not reliable", this.MAX_SIZE, this.mapSizes.get(this.MAX_SIZE - 1).intValue());
        Assert.assertEquals(this.MAX_SIZE, this.concurrentMap.size());
    }
}