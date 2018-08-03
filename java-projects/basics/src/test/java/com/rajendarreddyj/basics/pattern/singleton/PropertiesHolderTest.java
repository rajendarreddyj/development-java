package com.rajendarreddyj.basics.pattern.singleton;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author rajendarreddy.jagapathi
 */
public class PropertiesHolderTest {

    @Test
    public void test() throws IOException {
        Assert.assertNotNull(PropertiesHolder.getInstance().getPropertyValue("jdbc.driver"));
    }

}
