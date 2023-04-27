package bankManagement.accountService.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="bm_transaction")
@Inheritance(strategy = InheritanceType.JOINED)
public class Transaction {

    @Id
    @Column(name="transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="wording")
    private String wording;

    @Column(name="payment")
    private Float payment;

    @Column(name="withdraw")
    private Float withdraw;

    @Column(name="execution_date")
    @Temporal(TemporalType.DATE)
    private Date executionDate;

    @Column(name="value_date")
    @Temporal(TemporalType.DATE)
    private Date valueDate;

    @Column(name="balance")
    private Float balance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    public Transaction() {
    }

    public Transaction(Integer transaction_id, String wording, Float payment, Float withdraw, Date execution_date, Date value_date,
            Float balance, Account account) {
        this.id = transaction_id;
        this.wording = wording;
        this.payment = payment;
        this.withdraw = withdraw;
        this.executionDate = execution_date;
        this.valueDate = value_date;
        this.balance = balance;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float amount) {
        this.payment = amount;
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

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Float getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Float withdraw) {
        this.withdraw = withdraw;
    }
}
