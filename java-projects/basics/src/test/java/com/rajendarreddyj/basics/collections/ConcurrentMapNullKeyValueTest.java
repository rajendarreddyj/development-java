package com.rajendarreddyj.basics.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author rajendarreddy
 *
 */
public class ConcurrentMapNullKeyValueTest {
    ConcurrentMap<String, Object> concurrentMap;

    @Before
    public void setup() {
        this.concurrentMap = new ConcurrentHashMap<>();
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenGetWithNullKey_thenThrowsNPE() {
        this.concurrentMap.get(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenGetOrDefaultWithNullKey_thenThrowsNPE() {
        this.concurrentMap.getOrDefault(null, new Object());
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenPutWithNullKey_thenThrowsNPE() {
        this.concurrentMap.put(null, new Object());
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenPutNullValue_thenThrowsNPE() {
        this.concurrentMap.put("test", null);
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMapAndKeyAbsent_whenPutWithNullKey_thenThrowsNPE() {
        this.concurrentMap.putIfAbsent(null, new Object());
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMapAndMapWithNullKey_whenPutNullKeyMap_thenThrowsNPE() {
        Map<String, Object> nullKeyMap = new HashMap<>();
        nullKeyMap.put(null, new Object());
        this.concurrentMap.putAll(nullKeyMap);
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMapAndMapWithNullValue_whenPutNullValueMap_thenThrowsNPE() {
        Map<String, Object> nullValueMap = new HashMap<>();
        nullValueMap.put("test", null);
        this.concurrentMap.putAll(nullValueMap);
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenReplaceNullKeyWithValues_thenThrowsNPE() {
        this.concurrentMap.replace(null, new Object(), new Object());
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenReplaceWithNullNewValue_thenThrowsNPE() {
        Object o = new Object();
        this.concurrentMap.replace("test", o, null);
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenReplaceOldNullValue_thenThrowsNPE() {
        Object o = new Object();
        this.concurrentMap.replace("test", null, o);
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenReplaceWithNullValue_thenThrowsNPE() {
        this.concurrentMap.replace("test", null);
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenReplaceNullKey_thenThrowsNPE() {
        this.concurrentMap.replace(null, "test");
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenReplaceAllMappingNull_thenThrowsNPE() {
        this.concurrentMap.put("test", new Object());
        this.concurrentMap.replaceAll((s, o) -> null);
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenRemoveNullKey_thenThrowsNPE() {
        this.concurrentMap.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenRemoveNullKeyWithValue_thenThrowsNPE() {
        this.concurrentMap.remove(null, new Object());
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenMergeNullKeyWithValue_thenThrowsNPE() {
        this.concurrentMap.merge(null, new Object(), (o, o2) -> o2);
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenMergeKeyWithNullValue_thenThrowsNPE() {
        this.concurrentMap.put("test", new Object());
        this.concurrentMap.merge("test", null, (o, o2) -> o2);
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMapAndAssumeKeyAbsent_whenComputeWithNullKey_thenThrowsNPE() {
        this.concurrentMap.computeIfAbsent(null, s -> s);
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMapAndAssumeKeyPresent_whenComputeWithNullKey_thenThrowsNPE() {
        this.concurrentMap.computeIfPresent(null, (s, o) -> o);
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenComputeWithNullKey_thenThrowsNPE() {
        this.concurrentMap.compute(null, (s, o) -> o);
    }

    @Test
    public void givenConcurrentHashMap_whenMergeKeyRemappingNull_thenRemovesMapping() {
        Object oldValue = new Object();
        this.concurrentMap.put("test", oldValue);
        this.concurrentMap.merge("test", new Object(), (o, o2) -> null);
        Assert.assertNull(this.concurrentMap.get("test"));
    }

    @Test
    public void givenConcurrentHashMapAndKeyAbsent_whenComputeWithKeyRemappingNull_thenRemainsAbsent() {
        this.concurrentMap.computeIfPresent("test", (s, o) -> null);
        Assert.assertNull(this.concurrentMap.get("test"));
    }

    @Test
    public void givenKeyPresent_whenComputeIfPresentRemappingNull_thenMappingRemoved() {
        Object oldValue = new Object();
        this.concurrentMap.put("test", oldValue);
        this.concurrentMap.computeIfPresent("test", (s, o) -> null);
        Assert.assertNull(this.concurrentMap.get("test"));
    }

    @Test
    public void givenKeyPresent_whenComputeRemappingNull_thenMappingRemoved() {
        Object oldValue = new Object();
        this.concurrentMap.put("test", oldValue);
        this.concurrentMap.compute("test", (s, o) -> null);
        Assert.assertNull(this.concurrentMap.get("test"));
    }
}
