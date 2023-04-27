package bankManagement.accountService.domain;

import java.util.Date;

public class CurrentAccountResponse extends AccountResponse {
    private int bankOverdraft;

    public CurrentAccountResponse() {
    }

    public CurrentAccountResponse(String iban, String label, Date creation_date, String account_type, float balance,
            int bankOverdraft) {
        super(iban, label, creation_date, account_type, balance);
        this.bankOverdraft = bankOverdraft;
    }

    public CurrentAccountResponse(int id, String iban, String label, Date creation_date, String account_type,
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
