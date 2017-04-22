package com.rajendarreddyj.model.project;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class Developer {
    private final String firstName;
    private final String lastName;
    private final String mail;
    private final List<Role> roles;

    public Developer(final String firstName, final String lastName, final String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.roles = new ArrayList<>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getMail() {
        return this.mail;
    }

    public Developer addRole(final Role role) {
        this.roles.add(role);
        return this;
    }

    public List<Role> getRoles() {
        return this.roles;
    }
}
