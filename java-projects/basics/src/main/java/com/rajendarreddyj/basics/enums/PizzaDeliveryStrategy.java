package com.rajendarreddyj.basics.enums;

import java.util.logging.Logger;

/**
 * @author rajendarreddy
 *
 */
public enum PizzaDeliveryStrategy {
    
    EXPRESS {
        @Override
        public void deliver(final Pizza pz) {
            logger.info("Pizza will be delivered in express mode");
        }
    },
    NORMAL {
        @Override
        public void deliver(final Pizza pz) {
            logger.info("Pizza will be delivered in normal mode");
        }
    };
    private static final Logger logger = Logger.getAnonymousLogger();
    public abstract void deliver(Pizza pz);
}
