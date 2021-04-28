package com.app.dbutil;

import com.app.BusinessException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostGresConnection {

    private PostGresConnection(){}
    private static Connection connection;

    //the first block which gets executed and only once.
    static {
        //Establishing a connection between Java and
        try {
            Class.forName("org.postgresql.Driver");
            //declaring username and password. Passing the information into
            String url = "jdbc:postgresql://myfirstdb.clizqefgg5zs.us-east-2.rds.amazonaws.com:5432/postgres";
            //environment variable for usernames
            String username = System.getenv("database_username");
           //environment variables for password
            String password = System.getenv("database_password");
            if (username == null || password == null)
                throw new BusinessException("Database credentials are null did you set your environment variables");

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


        public static Connection getConnection(){
            return connection;
        }

    }
