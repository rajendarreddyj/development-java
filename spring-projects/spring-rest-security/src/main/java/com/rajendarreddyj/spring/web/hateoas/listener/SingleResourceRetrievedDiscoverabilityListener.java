package com.rajendarreddyj.spring.web.hateoas.listener;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;
import com.rajendarreddyj.spring.web.hateoas.event.SingleResourceRetrievedEvent;
import com.rajendarreddyj.spring.web.util.LinkUtil;

@Component
class SingleResourceRetrievedDiscoverabilityListener implements ApplicationListener<SingleResourceRetrievedEvent> {

    @Override
    public void onApplicationEvent(final SingleResourceRetrievedEvent resourceRetrievedEvent) {
        Preconditions.checkNotNull(resourceRetrievedEvent);

        final HttpServletResponse response = resourceRetrievedEvent.getResponse();
        this.addLinkHeaderOnSingleResourceRetrieval(response);
    }

    void addLinkHeaderOnSingleResourceRetrieval(final HttpServletResponse response) {
        final String requestURL = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri().toASCIIString();
        final int positionOfLastSlash = requestURL.lastIndexOf("/");
        final String uriForResourceCreation = requestURL.substring(0, positionOfLastSlash);

        final String linkHeaderValue = LinkUtil.createLinkHeader(uriForResourceCreation, "collection");
        response.addHeader(HttpHeaders.LINK, linkHeaderValue);
    }

}