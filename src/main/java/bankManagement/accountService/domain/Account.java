package bankManagement.accountService.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="bm_account")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    
    @Id
    @Column(name="account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="iban")
    private String iban;

    @Column(name="label")
    private String label;

    @Column(name="creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    /*
     * In a bidirectional relation the "mappedBy" attribute
     * is necessary and it refers to the name of the attribute in the entity
     */
    @OneToMany(orphanRemoval=true, mappedBy = "account")
    private List<Transaction> transactions;

    public void addTransaction(Transaction tr){
        if(this.transactions == null) {this.transactions = new ArrayList<>();}
		this.transactions.add(tr);
    }
    
    public Account() {
    }

    public Account(Integer account_id, String iban, String label, Date creation_date, Client client) {
        this.id = account_id;
        this.iban = iban;
        this.label = label;
        this.creationDate = creation_date;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
