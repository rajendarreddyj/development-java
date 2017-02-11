package com.rajendarreddyj.basics.swing.demo.addressbook;

/**
 * @author rajendarreddy
 *
 */
public class PersonInfo {
    private String name, address, email;
    private int id, phone;

    // default constructor
    public PersonInfo() {
        this.name = "";
        this.address = "";
        this.email = "";
        this.id = 0;
        this.phone = 0;
    }

    public PersonInfo(final int id, final String name, final String address, final int phone, final String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    // param construcrtor with 4 values
    public PersonInfo(final String name, final String address, final int phone, final String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    // setters
    public void setId(final int i) {
        this.id = i;
    }

    public void setName(final String n) {
        this.name = n;
    }

    public void setAddress(final String a) {
        this.address = a;
    }

    public void setPhone(final int ph) {
        this.phone = ph;
    }

    public void setEmail(final String e) {
        this.email = e;
    }

    // getters
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public int getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }
}