package com.example.BankManagement.business.dto;

import java.util.Date;
import java.util.List;

public class ClientMediumDTO extends ClientBasicDTO{

    private List<Integer> acccounts;

    public ClientMediumDTO() {
    }

    public ClientMediumDTO(String lastname, String firstname, String email, String login, String password,
            Date accessionDate) {
        super(lastname, firstname, email, login, password, accessionDate);
    }

    public ClientMediumDTO(String lastname, String firstname, String email, String login, String password,
            Date accessionDate, List<Integer> acccounts) {
        super(lastname, firstname, email, login, password, accessionDate);
        this.acccounts = acccounts;
    }

    public ClientMediumDTO(int id, String lastname, String firstname, String email, String login, String password,
            Date accessionDate) {
        super(id, lastname, firstname, email, login, password, accessionDate);
    }

    public List<Integer> getAcccounts() {
        return acccounts;
    }

    public void setAcccounts(List<Integer> acccounts) {
        this.acccounts = acccounts;
    }
    
}
