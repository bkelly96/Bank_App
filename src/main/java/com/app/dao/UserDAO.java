package com.app.dao;

import com.app.BusinessException;
import com.app.model.User;

import java.sql.SQLException;

public interface UserDAO {

    //setting the rule that we want to create an employee
    public User createUser(User user) throws SQLException, BusinessException;
}
