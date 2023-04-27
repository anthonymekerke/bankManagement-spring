package bankManagement.accountService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bankManagement.accountService.domain.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer>{
    
    Optional<Account> findByIdAndClient_Login(Integer id, String client_login);
    List<Account> findByClient_Login(String client_login);
}
