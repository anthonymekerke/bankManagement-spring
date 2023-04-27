package bankManagement.accountService.domain;

import java.util.Date;

public class TransactionMediumDTO extends TransactionBasicDTO{
    
    private int account_number;

    public TransactionMediumDTO() {
    }

    public TransactionMediumDTO(Date execution_date, Date value_date, float withdrawal, float payment, String wording,
            float balance) {
        super(execution_date, value_date, withdrawal, payment, wording, balance);
    }    

    public TransactionMediumDTO(Date execution_date, Date value_date, float withdrawal, float payment, String wording,
            float balance, int account_number) {
        super(execution_date, value_date, withdrawal, payment, wording, balance);
        this.account_number = account_number;
    }

    public TransactionMediumDTO(int id, Date execution_date, Date value_date, float withdrawal, float payment,
            String wording, float balance) {
        super(id, execution_date, value_date, withdrawal, payment, wording, balance);
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }
}
