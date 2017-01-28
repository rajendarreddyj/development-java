package com.rajendarreddyj.ws.impl;

import javax.jws.WebService;

import com.rajendarreddyj.ws.HelloWorld;

// Service Implementation
@WebService(endpointInterface = "com.rajendarreddyj.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String getHelloWorldAsString(final String name) {
        return "Hello World JAX-WS " + name;
    }

}
