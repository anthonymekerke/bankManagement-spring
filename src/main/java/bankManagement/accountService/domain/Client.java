package bankManagement.accountService.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="bm_client")
public class Client implements IEntity{
    
    @Id
    @Column(name="client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="lastname")
    private String lastname;

    @Column(name="firstname")
    private String firstname;

    @Column(name="password")
    private String password;

    @Column(name="date_adhesion")
    @Temporal(TemporalType.DATE)
    private Date accessionDate;

    @Column(name="email")
    private String email;

    @Column(name="login")
    private String login;

    @OneToMany(orphanRemoval = true, mappedBy = "client")
    private List<Account> accounts;

    public void addAccount(Account acc){
        if(this.accounts == null) {this.accounts = new ArrayList<>();}
		this.accounts.add(acc);
    }

    public Client() {
    }

    public Client(Integer client_id, String lastname, String firstname, String password, Date accessionDate,
            String email, String login) {
        this.id = client_id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
        this.accessionDate = accessionDate;
        this.email = email;
        this.login = login;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getAccessionDate() {
        return accessionDate;
    }

    public void setAccessionDate(Date date_adhesion) {
        this.accessionDate = date_adhesion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
