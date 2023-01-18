package com.example.BankManagement.business.dto;

import java.util.Date;

public class CurrentAccountBasicDTO extends AccountBasicDTO {
    private int bankOverdraft;

    public CurrentAccountBasicDTO() {
    }

    public CurrentAccountBasicDTO(String iban, String label, Date creation_date, String account_type, float balance,
            int bankOverdraft) {
        super(iban, label, creation_date, account_type, balance);
        this.bankOverdraft = bankOverdraft;
    }

    public CurrentAccountBasicDTO(int id, String iban, String label, Date creation_date, String account_type,
            float balance, int bankOverdraft) {
        super(id, iban, label, creation_date, account_type, balance);
        this.bankOverdraft = bankOverdraft;
    }

    public int getBankOverdraft() {
        return bankOverdraft;
    }

    public void setBankOverdraft(int bankOverdraft) {
        this.bankOverdraft = bankOverdraft;
    }    
}
