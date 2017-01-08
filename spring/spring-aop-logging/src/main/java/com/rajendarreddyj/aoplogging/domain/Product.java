package com.rajendarreddyj.aoplogging.domain;

public class Product {
    private final long id;
    private final String name;

    public Product(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product");
        sb.append("{id=").append(this.id);
        sb.append(", name='").append(this.name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}