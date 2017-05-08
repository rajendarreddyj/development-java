package com.rajendarreddyj.spring.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.base.Preconditions;
import com.rajendarreddyj.spring.persistence.model.Foo;
import com.rajendarreddyj.spring.persistence.service.IFooService;
import com.rajendarreddyj.spring.web.exception.MyResourceNotFoundException;
import com.rajendarreddyj.spring.web.hateoas.event.PaginatedResultsRetrievedEvent;
import com.rajendarreddyj.spring.web.hateoas.event.ResourceCreatedEvent;
import com.rajendarreddyj.spring.web.hateoas.event.SingleResourceRetrievedEvent;
import com.rajendarreddyj.spring.web.util.RestPreconditions;

@Controller
@RequestMapping(value = "/auth/foos")
public class FooController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private IFooService service;

    public FooController() {
        super();
    }

    // API

    @RequestMapping(method = RequestMethod.GET, value = "/count")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public long count() {
        return 2l;
    }

    // read - one

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Foo findById(@PathVariable("id") final Long id, final HttpServletResponse response) {
        final Foo resourceById = RestPreconditions.checkFound(this.service.findOne(id));

        this.eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
        return resourceById;
    }

    // read - all

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Foo> findAll() {
        return this.service.findAll();
    }

    @RequestMapping(params = { "page", "size" }, method = RequestMethod.GET)
    @ResponseBody
    public List<Foo> findPaginated(@RequestParam("page") final int page, @RequestParam("size") final int size, final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        final Page<Foo> resultPage = this.service.findPaginated(page, size);
        if (page > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        this.eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(Foo.class, uriBuilder, response, page, resultPage.getTotalPages(), size));

        return resultPage.getContent();
    }

    // write

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Foo create(@RequestBody final Foo resource, final HttpServletResponse response) {
        Preconditions.checkNotNull(resource);
        final Foo foo = this.service.create(resource);
        final Long idOfCreatedResource = foo.getId();

        this.eventPublisher.publishEvent(new ResourceCreatedEvent(this, response, idOfCreatedResource));

        return foo;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody final Foo resource) {
        Preconditions.checkNotNull(resource);
        RestPreconditions.checkFound(this.service.findOne(resource.getId()));
        this.service.update(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long id) {
        this.service.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.HEAD)
    @ResponseStatus(HttpStatus.OK)
    public void head(final HttpServletResponse resp) {
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        resp.setHeader("bar", "baz");
    }

}
