package com.rajendarreddyj.weld;

import javax.enterprise.inject.spi.CDI;

import org.jboss.weld.environment.se.Weld;

/**
 * @author rajendarreddy.jagapathi
 */
public class MainApplication {
    public static void main(final String[] args) {
        // try () {
        CDI<Object> container = new Weld().initialize();
        Checkout checkout = container.select(Checkout.class).get();

        checkout.finishCheckout();
        // }
    }
}
