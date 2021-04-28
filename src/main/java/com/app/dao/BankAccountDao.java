package com.app.dao;

import com.app.BusinessException;
import com.app.model.BankAccount;
import com.app.model.User;

import java.sql.SQLException;

public interface BankAccountDao {

    BankAccount createBankAccount(BankAccount bankAccount) throws SQLException, BusinessException;
    BankAccount viewBankAccount(int bankaccountid) throws SQLException, BusinessException;
    boolean adjustBankAccount(double amount, BankAccount bankAccount) throws SQLException, BusinessException;

}
