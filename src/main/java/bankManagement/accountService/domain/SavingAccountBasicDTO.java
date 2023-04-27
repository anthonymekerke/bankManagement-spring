package bankManagement.accountService.domain;

import java.util.Date;

public class SavingAccountBasicDTO extends AccountBasicDTO{
    private float interestRate;

    public SavingAccountBasicDTO() {
    }

    public SavingAccountBasicDTO(String iban, String label, Date creation_date, String account_type, float balance,
            float interestRate) {
        super(iban, label, creation_date, account_type, balance);
        this.interestRate = interestRate;
    }

    public SavingAccountBasicDTO(int id, String iban, String label, Date creation_date, String account_type,
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
