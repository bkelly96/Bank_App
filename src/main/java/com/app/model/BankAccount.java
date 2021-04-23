package com.app.model;
//Real World Entities are named model

import java.math.BigDecimal;

public class BankAccount {

    private int bankaccountid;
    //considering using BIGDECIMAL because of the double/float problem
    private BigDecimal account;
    private String userid;

    public BankAccount(int bankaccountid) {
        this.bankaccountid = bankaccountid;
    }

    public BankAccount(){

    }

    public BankAccount(int bankaccountid, BigDecimal account, String userid) {
        this.bankaccountid = bankaccountid;
        this.account = account;
        this.userid = userid;
    }

    public int getBankaccountid() {
        return bankaccountid;
    }

    public void setBankaccountid(int bankaccountid) {
        this.bankaccountid = bankaccountid;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "bankaccountid=" + bankaccountid +
                ", account=" + account +
                ", userid='" + userid + '\'' +
                '}';
    }
}

