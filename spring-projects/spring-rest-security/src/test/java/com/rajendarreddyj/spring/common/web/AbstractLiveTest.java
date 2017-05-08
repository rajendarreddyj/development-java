package com.rajendarreddyj.spring.common.web;

import static com.rajendarreddyj.spring.Consts.APPLICATION_PORT;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;
import com.rajendarreddyj.spring.test.IMarshaller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class AbstractLiveTest<T extends Serializable> {

    protected final Class<T> clazz;

    @Autowired
    protected IMarshaller marshaller;

    public AbstractLiveTest(final Class<T> clazzToSet) {
        super();

        Preconditions.checkNotNull(clazzToSet);
        this.clazz = clazzToSet;
    }

    // template method

    public abstract void create();

    public abstract String createAsUri();

    protected final void create(final T resource) {
        this.createAsUri(resource);
    }

    protected final String createAsUri(final T resource) {
        final Response response = this.createAsResponse(resource);
        Preconditions.checkState(response.getStatusCode() == 201, "create operation: " + response.getStatusCode());

        final String locationOfCreatedResource = response.getHeader(HttpHeaders.LOCATION);
        Preconditions.checkNotNull(locationOfCreatedResource);
        return locationOfCreatedResource;
    }

    final Response createAsResponse(final T resource) {
        Preconditions.checkNotNull(resource);
        final RequestSpecification givenAuthenticated = this.givenAuth();

        final String resourceAsString = this.marshaller.encode(resource);
        return givenAuthenticated.contentType(this.marshaller.getMime()).body(resourceAsString).post(this.getURL());
    }

    //

    protected String getURL() {
        return "http://localhost:" + APPLICATION_PORT + "/spring-rest-security/auth/foos";
    }

    protected final RequestSpecification givenAuth() {
        return RestAssured.given().auth().preemptive().basic("user1", "user1Pass");
    }

}
