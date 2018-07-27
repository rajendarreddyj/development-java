package com.rajendarreddyj.google.common.collect;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class OrderingTest {
    List<Integer> numbers = new ArrayList<>();

    @Before
    public void initialize() {
        numbers.add(new Integer(5));
        numbers.add(new Integer(2));
        numbers.add(new Integer(15));
        numbers.add(new Integer(51));
        numbers.add(new Integer(53));
        numbers.add(new Integer(35));
        numbers.add(new Integer(45));
        numbers.add(new Integer(32));
        numbers.add(new Integer(43));
        numbers.add(new Integer(16));
    }
    
    @Test
    public void testAllEqual() {
        Ordering<Object> comparator = Ordering.allEqual();
        Assert.assertSame(comparator, comparator.reverse());

        Assert.assertEquals(0, comparator.compare(null, null));
        Assert.assertEquals(0, comparator.compare(new Object(), new Object()));
        Assert.assertEquals(0, comparator.compare("apples", "oranges"));
        Assert.assertEquals("Ordering.allEqual()", comparator.toString());

        List<String> strings = ImmutableList.of("b", "a", "d", "c");
        Assert.assertEquals(strings, comparator.sortedCopy(strings));
        Assert.assertEquals(strings, comparator.immutableSortedCopy(strings));
      }
}
