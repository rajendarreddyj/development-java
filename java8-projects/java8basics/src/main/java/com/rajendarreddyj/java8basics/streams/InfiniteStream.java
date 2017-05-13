package com.rajendarreddyj.java8basics.streams;

import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * @author rajendarreddy
 *
 */
public class InfiniteStream {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        doWhileOldWay();

        doWhileStreamWay();

    }

    private static void doWhileOldWay() {

        int i = 0;
        while (i < 10) {
            logger.info(String.valueOf(i));
            i++;
        }
    }

    private static void doWhileStreamWay() {
        Stream<Integer> integers = Stream.iterate(0, i -> i + 1);
        integers.limit(10).forEach(System.out::println);
    }
}
