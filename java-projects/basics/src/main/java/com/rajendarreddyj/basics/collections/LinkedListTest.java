package com.rajendarreddyj.basics.collections;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

/**
 * @author rajendarreddy
 *
 */
public class LinkedListTest {
    private static final Logger logger = Logger.getAnonymousLogger();

    public static void main(final String[] args) {

        String[] str = { "raj", "raj2", "raj3", "raj4" };
        List<String> list1 = new LinkedList<>();
        for (String string : str) {
            list1.add(string);
        }
        String[] str2 = { "raj2", "raj3", "raj4" };
        List<String> list2 = new LinkedList<>();
        for (String string : str2) {
            list2.add(string);
        }
        list1.addAll(list2);
        printMe(list1);
        removeStuff(list1, 2, 5);
        printMe(list1);
        reverseMe(list1);
    }

    private static void reverseMe(final List<String> list1) {
        ListIterator<String> rev = list1.listIterator(list1.size());
        while (rev.hasPrevious()) {
            logger.info(rev.previous() + "\t");
        }
    }

    private static void removeStuff(final List<String> list1, final int from, final int to) {
        list1.subList(from, to).clear();

    }

    private static void printMe(final List<String> list1) {
        for (String string : list1) {
            logger.info(string + "\n");
        }
    }
}
