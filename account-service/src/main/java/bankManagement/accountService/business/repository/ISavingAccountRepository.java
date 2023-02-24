package bankManagement.accountService.business.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bankManagement.accountService.business.entity.SavingAccount;

@Repository
public interface ISavingAccountRepository extends JpaRepository<SavingAccount, Integer>{
 
    Optional<SavingAccount> findByIdAndClient_Login(Integer id, String client_login);
}
