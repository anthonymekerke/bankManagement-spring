package com.example.BankManagement.business.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class TransactionFullDTO extends TransactionBasicDTO{
    
    @NotNull
    private AccountBasicDTO account;

    public TransactionFullDTO() {
    }

    public TransactionFullDTO(Date execution_date, Date value_date, float withdrawal, float payment, String wording,
            float balance) {
        super(execution_date, value_date, withdrawal, payment, wording, balance);
    }    

    public TransactionFullDTO(Date execution_date, Date value_date, float withdrawal, float payment, String wording,
            float balance, AccountBasicDTO account) {
        super(execution_date, value_date, withdrawal, payment, wording, balance);
        this.account = account;
    }

    public TransactionFullDTO(int id, Date execution_date, Date value_date, float withdrawal, float payment,
            String wording, float balance) {
        super(id, execution_date, value_date, withdrawal, payment, wording, balance);
    }

    public AccountBasicDTO getAccount() {
        return account;
    }

    public void setAccount(AccountBasicDTO account) {
        this.account = account;
    }

}
