package com.rajendarreddyj.google.common.collect;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

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
    
}
