package com.rajendarreddyj.ws.publish;

import javax.xml.ws.Endpoint;

import com.rajendarreddyj.ws.impl.HelloWorldImpl;

// Endpoint publisher
public class HelloWorldPublisher {

    public static void main(final String[] args) {
        Endpoint.publish("http://localhost:9999/ws/hello", new HelloWorldImpl());
    }

}