package com.rajendarreddyj.ws.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import org.junit.Assert;
import org.junit.Test;

import com.rajendarreddyj.ws.HelloWorld;
import com.rajendarreddyj.ws.impl.HelloWorldImpl;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class HelloWorldITest {
    @Test
    public void testHelloWorldService() throws Exception {
        // Publishes the SOAP Web Service
        Endpoint endpoint = Endpoint.publish("http://localhost:9999/ws/hello", new HelloWorldImpl());
        Assert.assertTrue(endpoint.isPublished());
        Assert.assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http", endpoint.getBinding().getBindingID());
        URL url = new URL("http://localhost:9999/ws/hello?wsdl");
        // 1st argument service URI, refer to wsdl document above
        // 2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://impl.ws.rajendarreddyj.com/", "HelloWorldImplService");

        Service service = Service.create(url, qname);
        HelloWorld hello = service.getPort(HelloWorld.class);
        // Invokes the web service
        Assert.assertEquals("Hello World JAX-WS " + "rajendarreddyj", hello.getHelloWorldAsString("rajendarreddyj"));

        // Unpublishes the SOAP Web Service
        endpoint.stop();
        Assert.assertFalse(endpoint.isPublished());
    }

}
