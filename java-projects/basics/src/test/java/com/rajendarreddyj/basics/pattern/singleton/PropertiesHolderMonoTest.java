package com.rajendarreddyj.basics.pattern.singleton;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author rajendarreddy.jagapathi
 */
public class PropertiesHolderMonoTest {

    @Test
    public void test() {
        Assert.assertNotNull(PropertiesHolderMono.getPropertyValue("jdbc.driver"));
    }

}
