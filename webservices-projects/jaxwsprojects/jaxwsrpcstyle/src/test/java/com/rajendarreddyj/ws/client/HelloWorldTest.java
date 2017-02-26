package com.rajendarreddyj.ws.client;

import org.junit.Assert;
import org.junit.Test;

import com.rajendarreddyj.ws.HelloWorld;
import com.rajendarreddyj.ws.impl.HelloWorldImpl;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class HelloWorldTest {
    @Test
    public void testHelloWorldAsString() throws Exception {
        HelloWorld helloWorld = new HelloWorldImpl();
        Assert.assertEquals("Hello World JAX-WS " + "rajendarreddyj", helloWorld.getHelloWorldAsString("rajendarreddyj"));
    }

}
