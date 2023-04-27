package bankManagement.accountService.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="bm_current_account")
public class CurrentAccount extends Account{
    
    @Column(name="bank_overdraft")
    private Integer bankOverdraft;

    public CurrentAccount() {
    }

    public CurrentAccount(Integer account_id, String iban, String label, Date creation_date, Integer bank_overdraft, Client client) {
        super(account_id, iban, label, creation_date, client);
        this.bankOverdraft = bank_overdraft;
    }

    public Integer getBankOverdraft() {
        return bankOverdraft;
    }

    public void setBankOverdraft(Integer bank_overdraft) {
        this.bankOverdraft = bank_overdraft;
    }

}
