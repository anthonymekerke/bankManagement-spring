package bankManagement.accountService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bankManagement.accountService.domain.CurrentAccount;

@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Integer>{

    Optional<CurrentAccount> findByIdAndClient_Login(Integer id, String client_login);
}
