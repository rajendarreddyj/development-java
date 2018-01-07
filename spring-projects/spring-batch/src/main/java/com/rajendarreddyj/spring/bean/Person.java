package com.rajendarreddyj.spring.bean;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class Person {
    private String lastName;
    private String firstName;

    public Person() {

    }

    public Person(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "firstName: " + this.firstName + ", lastName: " + this.lastName;
    }

}
