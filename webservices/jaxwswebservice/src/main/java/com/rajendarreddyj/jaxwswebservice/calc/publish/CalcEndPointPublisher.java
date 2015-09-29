package com.rajendarreddyj.jaxwswebservice.calc.publish;

import javax.xml.ws.Endpoint;

import com.rajendarreddyj.jaxwswebservice.calc.Calculator;

/**
 * @author rajendarreddy
 *
 */
public class CalcEndPointPublisher {
	 public static void main(String[] args) {
	        Endpoint.publish("http://localhost:8080/CalcWS/Calculator",
	                        new Calculator());
	    }

}
