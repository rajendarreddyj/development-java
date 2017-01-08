package com.rajendarreddyj.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;

/**
 * @author rajendarreddy
 *
 */
public class Customer {

    @Id
    private String id;

    private String firstName;
    private String lastName;

    public Customer() {
    }

    public Customer(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%s, firstName='%s', lastName='%s']", this.id, this.firstName, this.lastName);
    }
}
