package com.rajendarreddyj.basics.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.logging.Logger;

public class NavigableSetDemo {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        NavigableSet<String> navigableSet = new TreeSet<>(Arrays.asList("X", "B", "A", "Z", "T"));
        Iterator<String> iterator = navigableSet.descendingIterator();
        logger.info("Original Set :");
        while (iterator.hasNext()) {
            logger.info(iterator.next());
        }
        iterator = navigableSet.iterator();
        logger.info("Sorted Navigable Set :");
        while (iterator.hasNext()) {
            logger.info(iterator.next());
        }
        System.out.printf("Head Set : %s.%n", navigableSet.headSet("X"));
        System.out.printf("Tail Set : %s.%n", navigableSet.tailSet("T", false));
        System.out.printf("Sub Set : %s.%n", navigableSet.subSet("B", true, "X", true));
        System.out.printf("Last Element : %s%n", navigableSet.last());
        System.out.printf("First Element : %s%n", navigableSet.first());
        System.out.printf("Reverse Set : %s%n", navigableSet.descendingSet());
        System.out.printf("Original Set : %s%n", navigableSet);
    }
}