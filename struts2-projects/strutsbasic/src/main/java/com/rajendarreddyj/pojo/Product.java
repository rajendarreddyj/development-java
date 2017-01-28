package com.rajendarreddyj.pojo;

/**
 * @author rajendarreddy
 */
public class Product {
    private int id;
    private String name;
    private float price;

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(final float price) {
        this.price = price;
    }

    public String execute() {
        return "success";
    }
}
