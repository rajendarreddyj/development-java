/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.rajendarreddyj.springbatchlistener.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rajendarreddyj.springbatchlistener.model.User;

/**
 *
 * @author rajendarreddy
 */
public class UserRowMapper implements RowMapper<User> {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
     * int)
     */
    @Override
    public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        User user = new User();
        user.setFirstName(rs.getString("firstName"));
        user.setMiddleName(rs.getString("middleName"));
        user.setLastName(rs.getString("lastName"));
        user.setCity(rs.getString("city"));
        user.setId(rs.getInt("id"));
        return user;
    }

}
