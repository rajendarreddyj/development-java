package com.rajendarreddyj.basics.enums;

/**
 * @author rajendarreddy
 *
 */
public enum PizzaDeliveryStrategy {
    EXPRESS {
        @Override
        public void deliver(final Pizza pz) {
            System.out.println("Pizza will be delivered in express mode");
        }
    },
    NORMAL {
        @Override
        public void deliver(final Pizza pz) {
            System.out.println("Pizza will be delivered in normal mode");
        }
    };
    public abstract void deliver(Pizza pz);
}
