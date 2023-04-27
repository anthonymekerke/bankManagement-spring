package bankManagement.accountService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bankManagement.accountService.domain.SavingAccount;

@Repository
public interface SavingAccountRepository extends JpaRepository<SavingAccount, Integer>{
 
    Optional<SavingAccount> findByIdAndClient_Login(Integer id, String client_login);
}
