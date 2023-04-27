package bankManagement.accountService.domain;

import java.util.Date;
import java.util.List;

public class AccountFullDTO extends AccountBasicDTO{
    
    private ClientBasicDTO client;
    private List<TransactionBasicDTO> lastTransactions;

    public AccountFullDTO() {
    }

    public AccountFullDTO(String iban, String label, Date creation_date, String account_type, float balance) {
        super(iban, label, creation_date, account_type, balance);
    }

    public AccountFullDTO(String iban, String label, Date creation_date, String account_type, float balance,
            ClientBasicDTO client, List<TransactionBasicDTO> transactions) {
        super(iban, label, creation_date, account_type, balance);
        this.client = client;
        this.lastTransactions = transactions;
    }

    public AccountFullDTO(int id, String iban, String label, Date creation_date, String account_type, float balance) {
        super(id, iban, label, creation_date, account_type, balance);
    }

    public ClientBasicDTO getClient() {
        return client;
    }
    public void setClient(ClientBasicDTO client) {
        this.client = client;
    }
    public List<TransactionBasicDTO> getLastTransactions() {
        return lastTransactions;
    }
    public void setLastTransactions(List<TransactionBasicDTO> transactions) {
        this.lastTransactions = transactions;
    }
    
}
