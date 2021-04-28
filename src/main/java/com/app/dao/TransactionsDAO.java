package com.app.dao;

import com.app.BusinessException;
import com.app.model.Transaction;
import com.app.model.User;

import java.sql.SQLException;

public interface TransactionsDAO {

    //setting the rule that we want to create an employee
    void addTransaction(Transaction transaction) throws SQLException, BusinessException;
}
