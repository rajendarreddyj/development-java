package com.rajendarreddyj.spring.persistence.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.Serializable;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

import com.rajendarreddyj.spring.persistence.IOperations;
import com.rajendarreddyj.spring.persistence.model.Foo;
import com.rajendarreddyj.spring.util.IDUtil;

public abstract class AbstractServicePersistenceIntegrationTest<T extends Serializable> {

    // tests

    // find - one

    @Test
    /**/public final void givenResourceDoesNotExist_whenResourceIsRetrieved_thenNoResourceIsReceived() {
        // When
        final Foo createdResource = this.getApi().findOne(IDUtil.randomPositiveLong());

        // Then
        assertNull(createdResource);
    }

    @Test
    public void givenResourceExists_whenResourceIsRetrieved_thenNoExceptions() {
        final Foo existingResource = this.persistNewEntity();
        this.getApi().findOne(existingResource.getId());
    }

    @Test
    public void givenResourceDoesNotExist_whenResourceIsRetrieved_thenNoExceptions() {
        this.getApi().findOne(IDUtil.randomPositiveLong());
    }

    @Test
    public void givenResourceExists_whenResourceIsRetrieved_thenTheResultIsNotNull() {
        final Foo existingResource = this.persistNewEntity();
        final Foo retrievedResource = this.getApi().findOne(existingResource.getId());
        assertNotNull(retrievedResource);
    }

    @Test
    public void givenResourceExists_whenResourceIsRetrieved_thenResourceIsRetrievedCorrectly() {
        final Foo existingResource = this.persistNewEntity();
        final Foo retrievedResource = this.getApi().findOne(existingResource.getId());
        assertEquals(existingResource, retrievedResource);
    }

    // find - one - by name

    // find - all

    @Test
    /**/public void whenAllResourcesAreRetrieved_thenNoExceptions() {
        this.getApi().findAll();
    }

    @Test
    /**/public void whenAllResourcesAreRetrieved_thenTheResultIsNotNull() {
        final List<Foo> resources = this.getApi().findAll();

        assertNotNull(resources);
    }

    @Test
    /**/public void givenAtLeastOneResourceExists_whenAllResourcesAreRetrieved_thenRetrievedResourcesAreNotEmpty() {
        this.persistNewEntity();

        // When
        final List<Foo> allResources = this.getApi().findAll();

        // Then
        assertThat(allResources, not(Matchers.<Foo> empty()));
    }

    @Test
    /**/public void givenAnResourceExists_whenAllResourcesAreRetrieved_thenTheExistingResourceIsIndeedAmongThem() {
        final Foo existingResource = this.persistNewEntity();

        final List<Foo> resources = this.getApi().findAll();

        assertThat(resources, hasItem(existingResource));
    }

    @Test
    /**/public void whenAllResourcesAreRetrieved_thenResourcesHaveIds() {
        this.persistNewEntity();

        // When
        final List<Foo> allResources = this.getApi().findAll();

        // Then
        for (final Foo resource : allResources) {
            assertNotNull(resource.getId());
        }
    }

    // create

    @Test(expected = RuntimeException.class)
    /**/public void whenNullResourceIsCreated_thenException() {
        this.getApi().create(null);
    }

    @Test
    /**/public void whenResourceIsCreated_thenNoExceptions() {
        this.persistNewEntity();
    }

    @Test
    /**/public void whenResourceIsCreated_thenResourceIsRetrievable() {
        final Foo existingResource = this.persistNewEntity();

        assertNotNull(this.getApi().findOne(existingResource.getId()));
    }

    @Test
    /**/public void whenResourceIsCreated_thenSavedResourceIsEqualToOriginalResource() {
        final Foo originalResource = this.createNewEntity();
        final Foo savedResource = this.getApi().create(originalResource);

        assertEquals(originalResource, savedResource);
    }

    @Test(expected = RuntimeException.class)
    public void whenResourceWithFailedConstraintsIsCreated_thenException() {
        final Foo invalidResource = this.createNewEntity();
        this.invalidate(invalidResource);

        this.getApi().create(invalidResource);
    }

    /**
     * -- specific to the persistence engine
     */
    @Test(expected = DataAccessException.class)
    @Ignore("Hibernate simply ignores the id silently and still saved (tracking this)")
    public void whenResourceWithIdIsCreated_thenDataAccessException() {
        final Foo resourceWithId = this.createNewEntity();
        resourceWithId.setId(IDUtil.randomPositiveLong());

        this.getApi().create(resourceWithId);
    }

    // update

    @Test(expected = RuntimeException.class)
    /**/public void whenNullResourceIsUpdated_thenException() {
        this.getApi().update(null);
    }

    @Test
    /**/public void givenResourceExists_whenResourceIsUpdated_thenNoExceptions() {
        // Given
        final Foo existingResource = this.persistNewEntity();

        // When
        this.getApi().update(existingResource);
    }

    /**
     * - can also be the ConstraintViolationException which now occurs on the update operation will not be translated;
     * as a consequence, it will be a TransactionSystemException
     */
    @Test(expected = RuntimeException.class)
    public void whenResourceIsUpdatedWithFailedConstraints_thenException() {
        final Foo existingResource = this.persistNewEntity();
        this.invalidate(existingResource);

        this.getApi().update(existingResource);
    }

    @Test
    /**/public void givenResourceExists_whenResourceIsUpdated_thenUpdatesArePersisted() {
        // Given
        final Foo existingResource = this.persistNewEntity();

        // When
        this.change(existingResource);
        this.getApi().update(existingResource);

        final Foo updatedResource = this.getApi().findOne(existingResource.getId());

        // Then
        assertEquals(existingResource, updatedResource);
    }

    // delete

    // @Test(expected = RuntimeException.class)
    // public void givenResourceDoesNotExists_whenResourceIsDeleted_thenException() {
    // // When
    // getApi().delete(IDUtil.randomPositiveLong());
    // }
    //
    // @Test(expected = RuntimeException.class)
    // public void whenResourceIsDeletedByNegativeId_thenException() {
    // // When
    // getApi().delete(IDUtil.randomNegativeLong());
    // }
    //
    // @Test
    // public void givenResourceExists_whenResourceIsDeleted_thenNoExceptions() {
    // // Given
    // final Foo existingResource = persistNewEntity();
    //
    // // When
    // getApi().delete(existingResource.getId());
    // }
    //
    // @Test
    // /**/public final void givenResourceExists_whenResourceIsDeleted_thenResourceNoLongerExists() {
    // // Given
    // final Foo existingResource = persistNewEntity();
    //
    // // When
    // getApi().delete(existingResource.getId());
    //
    // // Then
    // assertNull(getApi().findOne(existingResource.getId()));
    // }

    // template method

    protected Foo createNewEntity() {
        return new Foo(randomAlphabetic(6));
    }

    protected abstract IOperations<Foo> getApi();

    private final void invalidate(final Foo entity) {
        entity.setName(null);
    }

    private final void change(final Foo entity) {
        entity.setName(randomAlphabetic(6));
    }

    protected Foo persistNewEntity() {
        return this.getApi().create(this.createNewEntity());
    }

}
