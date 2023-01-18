package com.example.BankManagement.business.dto;

import java.util.Date;
import java.util.List;

public class ClientFullDTO extends ClientBasicDTO{

    List<AccountBasicDTO> accounts;

    public ClientFullDTO() {
    }

    public ClientFullDTO(String lastname, String firstname, String email, String login, String password,
            Date accessionDate) {
        super(lastname, firstname, email, login, password, accessionDate);
    }

    public ClientFullDTO(String lastname, String firstname, String email, String login, String password,
            Date accessionDate, List<AccountBasicDTO> accounts) {
        super(lastname, firstname, email, login, password, accessionDate);
        this.accounts = accounts;
    }

    public ClientFullDTO(int id, String lastname, String firstname, String email, String login, String password,
            Date accessionDate) {
        super(id, lastname, firstname, email, login, password, accessionDate);
    }

    public List<AccountBasicDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountBasicDTO> accounts) {
        this.accounts = accounts;
    }
    
}
