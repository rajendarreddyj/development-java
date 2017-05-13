package com.rajendarreddyj.basics.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BiConsumer;
import java.util.logging.Logger;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author rajendarreddy.jagapathi
 *
 */
@Ignore
public class MapPerformanceTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    static volatile int heartBeat = 0;
    // otherwise use forEach()
    static final boolean USE_ITERATION = false;
    static final boolean USE_LAMBDA = true;

    @Test
    public void testMapForPerformance() {
        logger.info((USE_ITERATION ? "Iterator" : USE_LAMBDA ? " forEach(lambda)" : "ForEach()") + " performance test");

        TestMap tests[] = { new TestMap(new HashMap<Integer, Integer>()), new TestMap(new TreeMap<Integer, Integer>()),
                new TestMap(new ConcurrentHashMap<Integer, Integer>()), new TestMap(new ConcurrentSkipListMap<Integer, Integer>()),

        };
        int sizes[] = new int[] { 1, 3, 10, 30, 100, 300, 1000, 3000, 10_000, 30_000, 100_000, 300_000, 1000_000, 3_000_000, 10_000_000 };

        // Just increment heartBeat every so often. It is volatile.
        // Reading it is very fast.
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    heartBeat++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();

        for (int i = 0; i < sizes.length; i++) {
            for (TestMap test : tests) {
                test.fillTo(sizes[i]);
            }
            // warmup
            for (TestMap test : tests) {
                int nextHeartBeat = heartBeat + 20;
                while (heartBeat < nextHeartBeat) {
                    if (USE_ITERATION) {
                        test.testIterator();
                    } else if (USE_LAMBDA) {
                        test.testForEachLambda();
                    } else {
                        test.testForEach();
                    }
                }
            }
            for (TestMap test : tests) {
                test.time = 0;
                test.loops = 0;
                long t0 = System.nanoTime();
                int nextHeartBeat = heartBeat + 30;
                while (heartBeat < nextHeartBeat) {
                    if (USE_ITERATION) {
                        test.testIterator();
                    } else if (USE_LAMBDA) {
                        test.testForEachLambda();
                    } else {
                        test.testForEach();
                    }
                }
                long t1 = System.nanoTime();
                test.time += (t1 - t0);
            }
            for (TestMap test : tests) {
                test.printResult();
                // logger.info("---------------");
            }
        }

    }
}

class TestMap implements BiConsumer<Integer, Integer> {
    // The total provides a tangible result to prevent optimizing-out
    long total = 0;
    long time = 0;
    int size = 0;
    long loops = 1;
    Map<Integer, Integer> map;

    TestMap(final Map<Integer, Integer> map) {
        this.map = map;
    }

    void fillTo(final int newSize) {
        Random random = new Random(this.size);
        while (this.size < newSize) {
            Integer n = new Integer(random.nextInt());
            this.map.put(n, n);
            this.size++;
        }
    }

    void testIterator() {
        for (Integer v : this.map.values()) {
            this.total += v.intValue();
        }
        this.loops++;
    }

    // This has the same effect and is 'terser'
    void testForEachLambda() {
        this.map.forEach((k, v) -> this.total += v);
        this.loops++;
    }

    void testForEach() {
        this.map.forEach(this);
        this.loops++;
    }

    // Implement BiConsumer for forEach()
    @Override
    public void accept(final Integer k, final Integer v) {
        this.total += k.intValue();
    }

    void printResult() {
        double seconds = this.time / 1e9;
        System.out.printf("%22s size=% 9d entries/s(K)=% 11.3f total=%d\n", this.map.getClass().getSimpleName(), this.size,
                (this.size * this.loops) / seconds / 1e3, this.total);

    }
}
