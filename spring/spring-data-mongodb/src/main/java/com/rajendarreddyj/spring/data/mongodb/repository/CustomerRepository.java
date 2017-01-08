package com.rajendarreddyj.spring.data.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rajendarreddyj.spring.data.mongodb.model.Customer;

/**
 * @author rajendarreddy
 *
 */
@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(@Param("firstName") String firstName);

    public List<Customer> findByLastName(@Param("lastName") String lastName);

}
