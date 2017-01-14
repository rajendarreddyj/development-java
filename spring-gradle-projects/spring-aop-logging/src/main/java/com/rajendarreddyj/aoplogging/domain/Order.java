package com.rajendarreddyj.aoplogging.domain;

import java.util.Date;

public class Order {
    private final Status status;

    public static enum Status {
        PROCESSED, FAILED;

        private Status() {
        }
    }

    private final Date timestamp = new Date();
    private final Customer customer;
    private final Product product;

    public Order(final Status status, final Customer customer, final Product product) {
        this.status = status;
        this.customer = customer;
        this.product = product;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order");
        sb.append("{status=").append(this.status);
        sb.append(", timestamp=").append(this.timestamp);
        sb.append(", customer=").append(this.customer);
        sb.append(", product=").append(this.product);
        sb.append('}');
        return sb.toString();
    }
}