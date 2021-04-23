package com.app.model;
//Real World Entities are named model

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Transactions {

    private int transactionid;
    private String transactiontime;
    //private date transactiontime;
    //private BigDecimal deposit;
    //private BigDecimal withdrawal;
    private int bankaccountid;
    private double deposit;
    private double withdrawal;

   // Date date = new Date();
    //Timestamp ts=new Timestamp(date.getTime());
   // System.out.println(ts);


    public Transactions() {
    }

    /*
    public Transactions(int transactionid, String transactiontime, BigDecimal deposit, BigDecimal withdrawal, int bankaccountid) {
        this.transactionid = transactionid;
        this.transactiontime = transactiontime;
        this.deposit = deposit;
        this.withdrawal = withdrawal;
        this.bankaccountid = bankaccountid;
    }
     */

    public Transactions(int transactionid, String transactiontime, int bankaccountid, double deposit, double withdrawal) {
        this.transactionid = transactionid;
        this.transactiontime = transactiontime;
        this.bankaccountid = bankaccountid;
        this.deposit = deposit;
        this.withdrawal = withdrawal;
    }

    public int getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }

    public String getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(String transactiontime) {
        this.transactiontime = transactiontime;
    }

      /*
    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(BigDecimal withdrawal) {
        this.withdrawal = withdrawal;
    }
     */

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(double withdrawal) {
        this.withdrawal = withdrawal;
    }

    public int getBankaccountid() {
        return bankaccountid;
    }

    public void setBankaccountid(int bankaccountid) {
        this.bankaccountid = bankaccountid;
    }
}
