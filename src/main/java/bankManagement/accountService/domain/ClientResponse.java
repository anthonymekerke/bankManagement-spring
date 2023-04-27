package bankManagement.accountService.domain;

import java.util.Date;

public class ClientResponse {

    private int id;
    private String lastname;
    private String firstname;
    private String email;
    private String login;
    private String password;
    private Date accessionDate;

    public ClientResponse() {
    }

    public ClientResponse(String lastname, String firstname, String email, String login, String password,
            Date accessionDate) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.accessionDate = accessionDate;
    }

    public ClientResponse(int id, String lastname, String firstname, String email, String login, String password,
            Date accessionDate) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.accessionDate = accessionDate;
    }

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getAccessionDate() {
        return accessionDate;
    }

    public void setAccessionDate(Date accessionDate) {
        this.accessionDate = accessionDate;
    }
}
