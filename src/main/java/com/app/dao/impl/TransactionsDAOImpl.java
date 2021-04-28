package com.app.dao.impl;

import com.app.BusinessException;
import com.app.dao.TransactionsDAO;
import com.app.dbutil.PostGresConnection;
import com.app.model.Transaction;

import java.sql.*;

public class TransactionsDAOImpl implements TransactionsDAO {

    @Override
    public void addTransaction(Transaction transaction) throws SQLException, BusinessException {

        try {
            Connection connection = PostGresConnection.getConnection();
            String sql = "INSERT INTO bank_schema.transactions\n" +
                    "(transactiontime, value, bank_account_src_id, bankaccount_destination_id, transaction_type)\n" +
                    "VALUES(?, ?, ?, ?, ?);\n";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, transaction.getTransactiontime());
            preparedStatement.setBigDecimal(2, transaction.getValue());
            preparedStatement.setInt(3, transaction.getBank_account_source_id());
            preparedStatement.setInt(4, transaction.getBank_account_destination_id());
            preparedStatement.setString(5, transaction.getTransaction_type());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException err) {
            System.out.println("Error: " + err.getMessage());;
        }

    }
}
