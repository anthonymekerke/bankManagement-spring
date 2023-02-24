package bankManagement.accountService.business.dto;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import bankManagement.accountService.business.dto.validation.AlternateNullFields;
import bankManagement.accountService.business.dto.validation.StartWithPrefixes;

@AlternateNullFields(first = "withdraw", second = "payment")
public class TransactionBasicDTO implements IDTO{
    private int id;

    @Future(message="value date must be in the future or 'null'.")
    private Date valueDate;

    @NotBlank(message="wording must not be empty and start with given prefix")
    @StartWithPrefixes
    private String wording;

    @Positive(message="withdraw value must be greater than zero")
    private Float withdraw;

    @Positive(message="payment value must be greater than zero")
    private Float payment;

    private Date executionDate;
    private Float balance;

    public TransactionBasicDTO() {
    }

    public TransactionBasicDTO(Date execution_date, Date value_date, float withdrawal, float payment, String wording,
            float balance) {
        this.executionDate = execution_date;
        this.valueDate = value_date;
        this.withdraw = withdrawal;
        this.payment = payment;
        this.wording = wording;
        this.balance = balance;
    }

    public TransactionBasicDTO(int id, Date executionDate, Date value_date, float withdrawal, float payment,
            String wording, float balance) {
        this.id = id;
        this.executionDate = executionDate;
        this.valueDate = value_date;
        this.withdraw = withdrawal;
        this.payment = payment;
        this.wording = wording;
        this.balance = balance;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date execution_date) {
        this.executionDate = execution_date;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date value_date) {
        this.valueDate = value_date;
    }

    public Float getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Float withdrawal) {
        this.withdraw = withdrawal;
    }

    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }
}
