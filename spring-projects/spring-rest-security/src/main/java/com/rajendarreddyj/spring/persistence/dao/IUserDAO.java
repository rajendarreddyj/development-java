package com.rajendarreddyj.spring.persistence.dao;

import java.util.List;

import com.rajendarreddyj.spring.persistence.model.User;
import com.rajendarreddyj.spring.web.util.SearchCriteria;

public interface IUserDAO {
    List<User> searchUser(List<SearchCriteria> params);

    void save(User entity);
}
