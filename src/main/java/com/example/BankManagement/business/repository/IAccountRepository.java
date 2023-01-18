package com.example.BankManagement.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BankManagement.business.entity.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer>{
    
    List<Account> findByClient_Id(int client_id);
}
