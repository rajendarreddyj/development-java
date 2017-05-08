package com.rajendarreddyj.spring.common.web;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.Serializable;

import org.hamcrest.core.AnyOf;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.google.common.net.HttpHeaders;
import com.rajendarreddyj.spring.persistence.model.Foo;
import com.rajendarreddyj.spring.web.util.HTTPLinkHeaderUtil;

import io.restassured.response.Response;

public abstract class AbstractDiscoverabilityLiveTest<T extends Serializable> extends AbstractLiveTest<T> {

    public AbstractDiscoverabilityLiveTest(final Class<T> clazzToSet) {
        super(clazzToSet);
    }

    // tests

    // discoverability

    @Test
    public void whenInvalidPOSTIsSentToValidURIOfResource_thenAllowHeaderListsTheAllowedActions() {
        // Given
        final String uriOfExistingResource = this.createAsUri();

        // When
        final Response res = this.givenAuth().post(uriOfExistingResource);

        // Then
        final String allowHeader = res.getHeader(HttpHeaders.ALLOW);
        assertThat(allowHeader, AnyOf.<String> anyOf(containsString("GET"), containsString("PUT"), containsString("DELETE")));
    }

    @Test
    public void whenResourceIsCreated_thenUriOfTheNewlyCreatedResourceIsDiscoverable() {
        // When
        final Foo newResource = new Foo(randomAlphabetic(6));
        final Response createResp = this.givenAuth().contentType(MediaType.APPLICATION_JSON_VALUE).body(newResource).post(this.getURL());
        final String uriOfNewResource = createResp.getHeader(HttpHeaders.LOCATION);

        // Then
        final Response response = this.givenAuth().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).get(uriOfNewResource);

        final Foo resourceFromServer = response.body().as(Foo.class);
        assertThat(newResource, equalTo(resourceFromServer));
    }

    @Test
    public void whenResourceIsRetrieved_thenUriToGetAllResourcesIsDiscoverable() {
        // Given
        final String uriOfExistingResource = this.createAsUri();

        // When
        final Response getResponse = this.givenAuth().get(uriOfExistingResource);

        // Then
        final String uriToAllResources = HTTPLinkHeaderUtil.extractURIByRel(getResponse.getHeader("Link"), "collection");

        final Response getAllResponse = this.givenAuth().get(uriToAllResources);
        assertThat(getAllResponse.getStatusCode(), is(403));
    }

    // template method

}
