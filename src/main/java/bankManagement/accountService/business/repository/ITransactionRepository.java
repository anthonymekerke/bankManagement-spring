package bankManagement.accountService.business.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bankManagement.accountService.business.entity.Transaction;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Integer>{
    Optional<Transaction> findByIdAndAccount_IdAndAccount_Client_Login(int tr_id,int ac_id, String client_login);
    List<Transaction> findByAccount_Id(int account_id);
    List<Transaction> findByAccount_IdAndAccount_Client_Login(int account_id, String client_login);

    Optional<Transaction> findFirstByAccountIdOrderByValueDateDesc(int account_id);
    Optional<Transaction> findFirstByAccountIdAndBalanceNotNullOrderByValueDateDesc(int account_id);
}
