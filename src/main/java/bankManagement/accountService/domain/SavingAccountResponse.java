package bankManagement.accountService.domain;

import java.util.Date;

public class SavingAccountResponse extends AccountResponse{
    private float interestRate;

    public SavingAccountResponse() {
    }

    public SavingAccountResponse(String iban, String label, Date creation_date, String account_type, float balance,
            float interestRate) {
        super(iban, label, creation_date, account_type, balance);
        this.interestRate = interestRate;
    }

    public SavingAccountResponse(int id, String iban, String label, Date creation_date, String account_type,
            float balance, float interestRate) {
        super(id, iban, label, creation_date, account_type, balance);
        this.interestRate = interestRate;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }
}
