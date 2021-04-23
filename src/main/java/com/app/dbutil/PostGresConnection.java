package com.app.dbutil;

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
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String username = "postgres";
            String password = "postgres";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }


        public static Connection getConnection(){
            return connection;
        }

    }



/*
How to build Singleton Java class?
Disable the constructor
 */