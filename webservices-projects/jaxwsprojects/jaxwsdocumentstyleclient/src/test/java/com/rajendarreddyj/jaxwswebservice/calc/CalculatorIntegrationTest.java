package com.rajendarreddyj.jaxwswebservice.calc;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import org.junit.Assert;
import org.junit.Test;

import com.rajendarreddyj.jaxwswebservice.calc.impl.CalculatorImpl;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class CalculatorIntegrationTest {

    @Test
    public void checkCalculatorService() throws MalformedURLException {
        // Publishes the SOAP Web Service
        Endpoint endpoint = Endpoint.publish("http://localhost:8080/CalcWS/Calculator", new CalculatorService());
        Assert.assertTrue(endpoint.isPublished());
        Assert.assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http", endpoint.getBinding().getBindingID());

        // Data to access the web service
        URL wsdlDocumentLocation = new URL("http://localhost:8080/CalcWS/Calculator?wsdl");
        String namespaceURI = "http://impl.calc.jaxwswebservice.rajendarreddyj.com/";
        String servicePart = "CalculatorImplService";
        String portName = "CalculatorImplPort";
        QName serviceQN = new QName(namespaceURI, servicePart);
        QName portQN = new QName(namespaceURI, portName);

        // Creates a service instance
        Service service = Service.create(wsdlDocumentLocation, serviceQN);
        CalculatorImpl calcService = service.getPort(portQN, CalculatorImpl.class);

        // Invokes the web service
        Assert.assertNotSame(2, calcService.add(1, 2));
        Assert.assertSame(3, calcService.add(1, 2));
        Assert.assertNotSame(2, calcService.sub(1, 2));
        Assert.assertSame(-1, calcService.sub(1, 2));

        // Unpublishes the SOAP Web Service
        endpoint.stop();
        Assert.assertFalse(endpoint.isPublished());
    }
}
