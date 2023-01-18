package com.example.BankManagement.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BankManagement.business.entity.Transaction;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Integer>{
    Transaction findFirstByAccountIdOrderByValueDateDesc(int account_id);
    List<Transaction> findByAccount_Id(int account_id);
}
