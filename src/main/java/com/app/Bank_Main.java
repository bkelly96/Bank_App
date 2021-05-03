package com.app;

import com.app.dao.BankAccountDao;
import com.app.dao.TransactionsDAO;
import com.app.dao.UserDAO;
import com.app.dao.impl.BankAccountDAOImpl;
import com.app.dao.impl.TransactionsDAOImpl;
import com.app.dao.impl.UserDAOImpl;
import com.app.dbutil.PasswordHashing;
import com.app.dbutil.PostGresConnection;
import com.app.model.BankAccount;
import com.app.model.Transaction;
import com.app.model.User;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;

/**
 * @author Brian Kelly
 * @since 4/23/2021
 * @version 1.0
 */

public class Bank_Main {

    private static Logger log = Logger.getLogger(Bank_Main.class);
    static UserDAOImpl userDAO = new UserDAOImpl();
    static BankAccountDao bankAccountDao = new BankAccountDAOImpl();
    static TransactionsDAO transactionsDAO = new TransactionsDAOImpl();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        log.info("Welcome to THE BANK! Did you want to create an account or log in?");
        log.info("Please select an option:");
        log.info("\n1)Log in\n 2)Sign up \n 3 Leave");

        //this code will keep the user in the loop until they make a valid choice.
        int choice = 0;
        boolean valid = false;
        while (!valid) {
            log.info("Choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 3) {
                    valid = true;
                } else {
                    log.info("Please enter a number between 1 and 3");
                }
            } catch (NumberFormatException nfe) {
                log.info("Please enter a number");
            }
            log.info("-------------------");
        }

        switch (choice) {
            case 1://Login
                user = loginUsername(user, scanner);
                if (user == null) {
                    log.info("Log in failed");
                    break;
                }
                //checks for user inside of user object
                if (user.getUserlevel().equals("user")) {
                    userMenu(user, scanner);
                }
                //checks for employee level inside of user object
                if (user.getUserlevel().equals("employee")){
                   employeeMenu(user, scanner);
                }
                break;
            case 2://Signup
                createNewUser(user, scanner);
                break;
            case 3://Leave
                break;

        }
        scanner.close();
    }

    //These are the methods that are a result of the initial choice
    //-------------------------------------------------------'


    //this is the result of choice 1 and which will have an existing user log in
    public static User loginUsername(User user, Scanner scanner) {

        //Asks user for Existing Username
        log.info("Enter Username");
        //user inputs Username
        String userInput = scanner.nextLine();

        //checks username against User class
        if (!user.setUsername(userInput)) {
            log.info("Username is invalid");
            return null;
        }

        // Ask for password
        log.info("Enter Password");
        //user inputs Username
        userInput = scanner.nextLine();

        //checks username against User class
        if (!user.setPassword(userInput)) {
            log.info("Password is invalid");
            return null;
        }

        try {
            user = userDAO.loginUsername(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (user == null) {
            return null;
        }

        return user;

    }

    //This is the result of a user logging in

    public static void userMenu(User user, Scanner scanner) {

        System.out.println("Welcome to your Bank menu");
        log.info("\n1)Apply for Account\n 2)View Account \n 3 Deposit\n 4) Withdraw\n 5) Transfer \n 0) Exit");
        System.out.println("Please enter a number:");



        //this code will keep the user in the loop until they make a valid choice.
        int choice = 0;
        boolean valid = false;
        while (!valid) {
            System.out.println("Welcome to your Bank menu");
            log.info("\n1)Apply for Account\n 2)View Account \n 3 Deposit\n 4) Withdraw\n 5) Transfer \n 0) Exit");
            System.out.println("Please enter a number:");;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 3) {
                    valid = true;
                } else {
                    log.info("Please enter a number between 0 and 5");
                }
            } catch (NumberFormatException nfe) {
                log.info("Please enter a number");
            }
            log.info("-------------------");
        }

        switch (choice) {
            case 1://Apply for accounts
                applyForAccount(user, scanner);
                break;
            case 2://View Account
                viewAccount(user, scanner);
                break;
            case 3://Deposit
                depositAmmount(user, scanner);
                /*
                break;
            case 4://Withdraw
                break;
            case 5://Transfer
                break;
            case 6://Leave
                break;

*/
        }
    }

    //this is the result of an employee logging in
    public static void employeeMenu(User user, Scanner scanner){
        System.out.println("Welcome to the Employee Interface");
        log.info("\n1) List all Active Accounts\n 2)View Accounts \n 3 List Pending Accounts\n 4) List of All Transactions \n 0) Exit");
        System.out.println("Please enter a number:");


        //this code will keep the user in the loop until they make a valid choice.
        int choice = 0;
        boolean valid = false;
        while (!valid) {
            System.out.println("Welcome to the Employee Interface");
            log.info("\n1) List all Active Accounts\n 2)View Accounts \n 3 List Pending Accounts\n 4) List of All Transactions \n 0) Exit");
            System.out.println("Please enter a number:");;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 3) {
                    valid = true;
                } else {
                    log.info("Please enter a number between 0 and 4");
                }
            } catch (NumberFormatException nfe) {
                log.info("Please enter a number");
            }
            log.info("-------------------");
        }

        switch (choice) {
            case 1://List of All Transactions
                try {
                    transactionsDAO.listTransaction();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
                break;
/*            case 2://View Accounts
                viewAccount(user, scanner);
                break;
            case 3://List all Active Accounts
                break;
            case 4://List Pending Accounts
                transactionsDAO.listTransaction();
                break;
            case 0://Leave
                break;

        }*/
        }
    }

    private static void applyForAccount(User user, Scanner scanner) {
        System.out.println("What do you want the starting balance to be?");
        double startingBalance = scanner.nextDouble();

        BankAccount bankAccount = new BankAccount();

        bankAccount.setUserid(user.getUserid());

        if (bankAccount.setAccountbalance(new BigDecimal(startingBalance)) == false)
            log.info("This is amount is invalid");

        try {
            bankAccount = bankAccountDao.createBankAccount(bankAccount);
                System.out.println("Amount is valid");
                System.out.println(bankAccount);

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (BusinessException e) {
            e.printStackTrace();
        }


    }

    //method for viewing account
    private static void viewAccount(User user, Scanner scanner) {
        System.out.println("Please enter the bankaccountid of the account you would like to view");
        int choice = scanner.nextInt();

        try {
            BankAccount bankAccount = bankAccountDao.viewBankAccount(choice);
            if (bankAccount == null){
                return;
            }
            log.info(bankAccount.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (BusinessException e) {
            e.printStackTrace();
        }


    }

    //method for depositing ammount
    private static void depositAmmount(User user, Scanner scanner) {
        System.out.println("Which account do you want to deposit too?");
        int choice = scanner.nextInt();

        try {
            BankAccount bankaccount = bankAccountDao.viewBankAccount(choice);
            if (bankaccount==null){
                return;
            }
            System.out.println("How much would you like to deposit");
            double amount = scanner.nextDouble();
            if (amount < 0)
                log.info("Invalid amount");
            else if (bankAccountDao.adjustBankAccount(amount, bankaccount)==true) {
                log.info("Account updated successfully");
                Calendar calendar = Calendar.getInstance();
                java.util.Date now = calendar.getTime();
                Transaction transaction = new Transaction();
                transaction.setTransactiontime(new Timestamp(now.getTime()));
                transaction.setValue(new BigDecimal(amount));
                transaction.setBank_account_source_id(bankaccount.getBankaccountid());
                transaction.setBank_account_destination_id(bankaccount.getBankaccountid());
                transaction.setTransaction_type("deposit");

                transactionsDAO.addTransaction(transaction);
            }
            else{
                log.info("Account did not update");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (BusinessException e) {
            e.printStackTrace();
        }

    }

    //this is the result of choice 2 and will create a new user

    public static void createNewUser(User user, Scanner scanner){

        //Asks user for Username
        log.info("Enter Username");

        //This will create a temporary string that is sent to the User class to ensure that it meets
        //our requirements for user input; Specifically Username
        boolean userNameOK = false;
        do {

            log.info("Please create your username (only enter alphabetical characters and numerical values, between 2 - 20)");
            String temp = scanner.nextLine();

            //Stores the temporary String into the contact
            userNameOK = user.setUsername(temp);

            //This will print out additional instructions if the user fails to enter qualifying information
            if(!userNameOK) {

                System.out.println("\nPlease only use alphabetical characters and numerical values!\n");
            }

        } while(!userNameOK);

        //Asks user for Password
        log.info("Enter password");

        //This will create a temporary string that is sent to the User class to ensure that it meets
        //our requirements for user input; Specifically Password
        boolean passwordOK = false;
        do {

            log.info("Please create your password (enter any value, between 8 - 128 characters)");
            String temp = scanner.nextLine();

            //Stores the temporary String into the contact
            passwordOK = user.setPassword(temp);

            //This will print out additional instructions if the user fails to enter qualifying information
            if(!passwordOK) {

                System.out.println("\nPlease only use alphabetical characters, numbers, hyphens, and apostrophes!\n");
            }

        } while(!passwordOK);

        //Asks user for first name
        log.info("Enter first name");

        //This will create a temporary string that is sent to the User class to ensure that it meets
        //our requirements for user input; Specifically firstname
        boolean firstNameOK = false;
        do {

            log.info("Please create your First Name (only enter alphabetical characters, between 2 - 20)");
            String temp = scanner.nextLine();

            //Stores the temporary String into the contact
            firstNameOK = user.setFirstname(temp);

            //This will print out additional instructions if the user fails to enter qualifying information
            if(!firstNameOK) {

                System.out.println("\nPlease only use alphabetical characters!\n");
            }

        } while(!firstNameOK);

        //Asks user for last name
        log.info("Enter last name");

        //This will create a temporary string that is sent to the User class to ensure that it meets
        //our requirements for user input; Specifically lastname
        boolean lastNameOK = false;
        do {

            log.info("Please create your username (only enter alphabetical characters, between 2 - 20)");
            String temp = scanner.nextLine();

            //Stores the temporary String into the contact
            lastNameOK = user.setLastname(temp);

            //This will print out additional instructions if the user fails to enter qualifying information
            if(!lastNameOK) {

                System.out.println("\nPlease only use alphabetical characters!\n");
            }

        } while(!lastNameOK);

        //this sets the userlevel to user. When a new account is created their default access level will be user.
        user.setUserlevel("user");


        //instatiatng a new object and implementing UserDao
        //UserDAO userDAO=new UserDAOImpl();

        try {
            //passing the user
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
