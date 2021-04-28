package com.app.model;
//Real World Entities are named model

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Transactions {

    private int transactionid;
    private String transactiontime;
    private BigDecimal value;
    private int bank_account_source_id;
    private int bank_account_destination_id;
    private String transaction_type;


   // Date date = new Date();
    //Timestamp ts=new Timestamp(date.getTime());
   // System.out.println(ts);


    public Transactions() {
    }

    public Transactions(int transactionid, String transactiontime, int bank_account_source_id, int bank_account_destination_id, String transaction_type) {
        this.transactionid = transactionid;
        this.transactiontime = transactiontime;
        this.bank_account_source_id = bank_account_source_id;
        this.bank_account_destination_id = bank_account_destination_id;
        this.transaction_type = transaction_type;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getBank_account_source_id() {
        return bank_account_source_id;
    }

    public void setBank_account_source_id(int bank_account_source_id) {
        this.bank_account_source_id = bank_account_source_id;
    }

    public int getBank_account_destination_id() {
        return bank_account_destination_id;
    }

    public void setBank_account_destination_id(int bank_account_destination_id) {
        this.bank_account_destination_id = bank_account_destination_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }
}
