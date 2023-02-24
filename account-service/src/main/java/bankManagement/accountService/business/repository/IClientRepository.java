package bankManagement.accountService.business.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bankManagement.accountService.business.entity.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Integer>{
    Optional<Client> findByLogin(String login);
}
