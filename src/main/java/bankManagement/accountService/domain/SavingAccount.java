package bankManagement.accountService.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="bm_saving_account")
public class SavingAccount extends Account{
    
    @Column(name="interest_rate")
    private Float interestRate;

    public SavingAccount() {
    }

    public SavingAccount(Integer account_id, String iban, String label, Date creation_date, Float interest_rate, Client client) {
        super(account_id, iban, label, creation_date, client);
        this.interestRate = interest_rate;
    }

    public Float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Float interest_rate) {
        this.interestRate = interest_rate;
    }

}
