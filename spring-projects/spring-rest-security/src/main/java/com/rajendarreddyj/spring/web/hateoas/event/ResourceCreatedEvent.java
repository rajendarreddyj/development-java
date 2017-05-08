package com.rajendarreddyj.spring.web.hateoas.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class ResourceCreatedEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    private final HttpServletResponse response;
    private final long idOfNewResource;

    public ResourceCreatedEvent(final Object source, final HttpServletResponse response, final long idOfNewResource) {
        super(source);

        this.response = response;
        this.idOfNewResource = idOfNewResource;
    }

    // API

    public HttpServletResponse getResponse() {
        return this.response;
    }

    public long getIdOfNewResource() {
        return this.idOfNewResource;
    }

}
