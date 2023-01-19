package com.example.BankManagement.business.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BankManagement.business.entity.CurrentAccount;

@Repository
public interface ICurrentAccountRepository extends JpaRepository<CurrentAccount, Integer>{

    Optional<CurrentAccount> findByIdAndClient_Login(Integer id, String client_login);
}
