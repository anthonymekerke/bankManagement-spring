package bankManagement.accountService.domain;

import java.util.Date;

public class AccountResponse {
    
    private int id;
    
    private String iban;
    private String label;
    private Date creationDate;
    private String accountType;
    private float balance;

    public AccountResponse() {
    }

    public AccountResponse(String iban, String label, Date creation_date, String account_type, float balance) {
        this.iban = iban;
        this.label = label;
        this.creationDate = creation_date;
        this.accountType = account_type;
        this.balance = balance;
    }

    public AccountResponse(int id, String iban, String label, Date creation_date, String account_type, float balance) {
        this.id = id;
        this.iban = iban;
        this.label = label;
        this.creationDate = creation_date;
        this.accountType = account_type;
        this.balance = balance;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getIban() {
        return iban;
    }
    public void setIban(String iban) {
        this.iban = iban;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creation_date) {
        this.creationDate = creation_date;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String account_type) {
        this.accountType = account_type;
    }
    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }
}
