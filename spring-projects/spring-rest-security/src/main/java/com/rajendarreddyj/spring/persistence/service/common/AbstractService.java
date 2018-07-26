package com.rajendarreddyj.spring.persistence.service.common;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.rajendarreddyj.spring.persistence.IOperations;

@Transactional
public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

    // read - one

    @Transactional(readOnly = true)
    public T findOne(final long id) {
        return this.getDao().findById(id).get();
    }

    // read - all

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return Lists.newArrayList(this.getDao().findAll());
    }

    @Override
    public Page<T> findPaginated(final int page, final int size) {
        return this.getDao().findAll(new PageRequest(page, size));
    }

    // write

    @Override
    public T create(final T entity) {
        return this.getDao().save(entity);
    }

    @Override
    public T update(final T entity) {
        return this.getDao().save(entity);
    }

    @Override
    public void delete(final T entity) {
        this.getDao().delete(entity);
    }

    @Override
    public void deleteById(final long entityId) {
        this.getDao().deleteById(entityId);
    }

    protected abstract PagingAndSortingRepository<T, Long> getDao();

}
