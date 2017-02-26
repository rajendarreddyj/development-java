package com.rajendarreddyj.jaxwswebservice.calc.publish;

import javax.xml.ws.Endpoint;

import com.rajendarreddyj.jaxwswebservice.calc.impl.CalculatorImpl;

/**
 * @author rajendarreddy
 */
public class CalcEndPointPublisher {
    public static void main(final String[] args) {
        Endpoint.publish("http://localhost:8080/CalcWS/Calculator", new CalculatorImpl());
    }

}
