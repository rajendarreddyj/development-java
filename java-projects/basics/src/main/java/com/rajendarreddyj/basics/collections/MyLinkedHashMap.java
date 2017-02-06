package com.rajendarreddyj.basics.collections;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int MAX_ENTRIES = 5;

    public MyLinkedHashMap(final int initialCapacity, final float loadFactor, final boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected boolean removeEldestEntry(final Map.Entry eldest) {
        return this.size() > MAX_ENTRIES;
    }

}
