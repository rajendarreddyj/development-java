package com.rajendarreddyj.spring.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rajendarreddyj.spring.persistence.model.Foo;

public interface IFooDao extends JpaRepository<Foo, Long>, JpaSpecificationExecutor<Foo> {

    @Query("SELECT f FROM Foo f WHERE LOWER(f.name) = LOWER(:name)")
    Foo retrieveByName(@Param("name") String name);

}
