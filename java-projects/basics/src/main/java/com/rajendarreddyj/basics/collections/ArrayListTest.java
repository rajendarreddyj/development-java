package com.rajendarreddyj.basics.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author rajendarreddy
 *
 */
public class ArrayListTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        String[] str = { "raj", "raj2", "raj3", "raj4" };
        List<String> list1 = new ArrayList<>();
        for (String string : str) {
            list1.add(string);
        }
        String[] more = { "raj2", "raj3" };
        List<String> list2 = new ArrayList<>();

        for (String string : more) {
            list2.add(string);
        }
        for (int i = 0; i < list1.size(); i++) {
            System.out.printf("%s\n", list1.get(i));
        }
        for (int i = 0; i < list2.size(); i++) {
            System.out.printf("%s\t", list2.get(i));
        }
        editlist(list1, list2);
        logger.info("\nedited list");
        for (int i = 0; i < list1.size(); i++) {
            System.out.printf("%s\n", list1.get(i));
        }
    }

    private static void editlist(final Collection<String> list1, final Collection<String> list2) {
        Iterator<String> iterator = list1.iterator();
        while (iterator.hasNext()) {
            if (list2.contains(iterator.next())) {
                iterator.remove();
            }
        }

    }

}
