package com.rajendarreddyj.spring.persistence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.rajendarreddyj.spring.persistence.dao.IFooDao;
import com.rajendarreddyj.spring.persistence.model.Foo;
import com.rajendarreddyj.spring.persistence.service.IFooService;
import com.rajendarreddyj.spring.persistence.service.common.AbstractService;

@Service
@Transactional
public class FooService extends AbstractService<Foo> implements IFooService {

    @Autowired
    private IFooDao dao;

    public FooService() {
        super();
    }

    // API

    @Override
    protected PagingAndSortingRepository<Foo, Long> getDao() {
        return this.dao;
    }

    // custom methods

    @Override
    public Foo retrieveByName(final String name) {
        return this.dao.retrieveByName(name);
    }

    // overridden to be secured

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Foo> findAll() {
        return Lists.newArrayList(this.getDao().findAll());
    }

}
