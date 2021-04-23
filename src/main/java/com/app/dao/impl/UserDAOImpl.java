package com.app.dao.impl;

import com.app.BusinessException;
import com.app.dao.UserDAO;
import com.app.dbutil.PostGresConnection;
import com.app.model.User;

import java.sql.*;

public class UserDAOImpl implements UserDAO{

    @Override
    public User createUser(User user) throws SQLException, BusinessException {

        Connection connection = PostGresConnection.getConnection();
        String sql = "INSERT INTO bank_schema.user\n" +
                "(username, \"password\", customername, userlevel)\n" +
                "VALUES(?, ?, ?, ?);\n";
        PreparedStatement preparedStatement= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getCustomername());
        preparedStatement.setString(4, user.getUserlevel());

        //
        int c=preparedStatement.executeUpdate();
        if(c==1){
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                user.setUserid(resultSet.getInt(1));
            }
        }else{
            throw new BusinessException("Failure in regfistration...please retry...");
        }
        return user;
    }
}
