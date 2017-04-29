package com.rajendarreddyj.basics.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class SetTest {
    @Test
    public void givenTreeSet_whenRetrievesObjects_thenNaturalOrder() {
        Set<String> set = new TreeSet<>();
        set.add("Java");
        set.add("is");
        set.add("Awesome");

        assertEquals(3, set.size());
        assertTrue(set.iterator().next().equals("Awesome"));
    }

    @Test(expected = NullPointerException.class)
    public void givenTreeSet_whenAddNullObject_thenNullPointer() {
        Set<String> set = new TreeSet<>();
        set.add("Java");
        set.add("is");
        set.add(null);
    }

    @Test
    public void givenHashSet_whenAddNullObject_thenOK() {
        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("is");
        set.add(null);

        assertEquals(3, set.size());
    }

    @Test
    public void givenHashSetAndTreeSet_whenAddDuplicates_thenOnlyUnique() {
        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("Java");

        assertTrue(set.size() == 1);

        Set<String> set2 = new TreeSet<>();
        set2.add("Java");
        set2.add("Java");

        assertTrue(set2.size() == 1);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void givenHashSet_whenModifyWhenIterator_thenFailFast() {
        Set<String> set = new HashSet<>();
        set.add("Java");
        Iterator<String> it = set.iterator();

        while (it.hasNext()) {
            set.add("Awesome");
            it.next();
        }
    }

}
