package com.example.BankManagement.business.dto;

import java.util.Date;
import java.util.List;

public class AccountMediumDTO extends AccountBasicDTO{
    
    private int clientNumber;
    private List<Integer> lastTransactions;

    public AccountMediumDTO() {
    }    

    public AccountMediumDTO(String iban, String label, Date creation_date, String account_type, float balance) {
        super(iban, label, creation_date, account_type, balance);
    }  

    public AccountMediumDTO(String iban, String label, Date creation_date, String account_type, float balance,
            int client, List<Integer> last_transactions) {
        super(iban, label, creation_date, account_type, balance);
        this.clientNumber = client;
        this.lastTransactions = last_transactions;
    }

    public AccountMediumDTO(int id, String iban, String label, Date creation_date, String account_type, float balance) {
        super(id, iban, label, creation_date, account_type, balance);
    }

    public int getClientNumber() {
        return clientNumber;
    }
    public void setClientNumber(int client) {
        this.clientNumber = client;
    }
    public List<Integer> getLastTransactions() {
        return lastTransactions;
    }
    public void setLastTransactions(List<Integer> transactions) {
        this.lastTransactions = transactions;
    }
}
