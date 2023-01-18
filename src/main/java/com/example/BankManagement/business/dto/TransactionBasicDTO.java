package com.example.BankManagement.business.dto;

import java.util.Date;

public class TransactionBasicDTO implements IDTO{
    private int id;

    private Date executionDate;
    private Date valueDate;
    private Float withdraw;
    private Float payment;
    private String wording;
    private Float balance;

    public TransactionBasicDTO() {
    }

    public TransactionBasicDTO(Date execution_date, Date value_date, float withdrawal, float payment, String wording,
            float balance) {
        this.executionDate = execution_date;
        this.valueDate = value_date;
        this.withdraw = withdrawal;
        this.payment = payment;
        this.wording = wording;
        this.balance = balance;
    }

    public TransactionBasicDTO(int id, Date executionDate, Date value_date, float withdrawal, float payment,
            String wording, float balance) {
        this.id = id;
        this.executionDate = executionDate;
        this.valueDate = value_date;
        this.withdraw = withdrawal;
        this.payment = payment;
        this.wording = wording;
        this.balance = balance;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date execution_date) {
        this.executionDate = execution_date;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date value_date) {
        this.valueDate = value_date;
    }

    public Float getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Float withdrawal) {
        this.withdraw = withdrawal;
    }

    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }
}
