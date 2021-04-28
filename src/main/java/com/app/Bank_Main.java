package com.app;

import com.app.dao.UserDAO;
import com.app.dao.impl.UserDAOImpl;
import com.app.model.User;
import com.app.model.BankAccount;
import com.app.model.Transactions;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Brian Kelly
 * @since 4/24/2021
 * @version 1.0
 */

public class insertMain {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        User user = new User();

        //replace all of this with logger
        System.out.println("Enter username");
        user.setUsername(scanner.nextLine());
        System.out.println("Enter password");
        user.setPassword(scanner.nextLine());
        System.out.println("Enter name");
        user.setCustomername(scanner.nextLine());
        System.out.println("Enter userlevel");
        user.setUserlevel(scanner.nextLine());

        //instatiatng a new object and implementing UserDao
        UserDAO userDAO=new UserDAOImpl();
        try {
            //passing the use
            user=userDAO.createUser(user);
            if(user.getUserid()!=0){
                System.out.println("User registered successfully");
                System.out.println(user);
            }
        } catch (SQLException | BusinessException e) {
            System.out.println("Internal error occurred...please reach out");;
        }
    }
}
