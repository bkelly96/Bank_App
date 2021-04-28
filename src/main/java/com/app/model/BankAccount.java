package com.app.model;
//Real World Entities are named model

import java.math.BigDecimal;

public class BankAccount {

    private int bankaccountid;
    //considering using BIGDECIMAL because of the double/float problem
    private int userid;
    private BigDecimal accountbalance;
    private String statuses;

    public BankAccount(){

    }

    public BankAccount(int bankaccountid, BigDecimal account, int userid, String statuses) {
        this.bankaccountid = bankaccountid;
        this.accountbalance = account;
        this.userid = userid;
    }

    public BankAccount(int bankaccountid) {
        this.bankaccountid = bankaccountid;
    }


    public int getBankaccountid() {
        return bankaccountid;
    }

    public void setBankaccountid(int bankaccountid) {
        this.bankaccountid = bankaccountid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public BigDecimal getAccountbalance() {
        return accountbalance;
    }

    public boolean setAccountbalance(BigDecimal accountbalance) {

        if (accountbalance.intValue() < 0)
                return false;

        this.accountbalance = accountbalance;
        return true;
    }

    public String getStatuses() {
        if (statuses==null)
            return "pending";
        return statuses;
    }

    public void setStatuses(String statuses) {
        this.statuses = statuses;
    }


    @Override
    public String toString() {
        return "BankAccount{" +
                "bankaccountid=" + bankaccountid +
                ", userid='" + userid + '\'' +
                ", amount=" + accountbalance +
                ", Status='" + statuses+ '\'' +
                '}';
    }
}

