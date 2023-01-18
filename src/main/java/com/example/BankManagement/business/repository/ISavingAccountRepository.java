package com.example.BankManagement.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BankManagement.business.entity.SavingAccount;

@Repository
public interface ISavingAccountRepository extends JpaRepository<SavingAccount, Integer>{
    
}