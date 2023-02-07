package com.example.BankManagement.business.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BankManagement.business.entity.Transaction;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Integer>{
    Optional<Transaction> findFirstByAccountIdOrderByValueDateDesc(int account_id);
    Optional<Transaction> findByIdAndAccount_Client_Login(int tr_id, String client_login);
    List<Transaction> findByAccount_Id(int account_id);
    List<Transaction> findByAccount_IdAndAccount_Client_Login(int account_id, String client_login);
    boolean existsByAccount_IdAndAccount_Client_Login(int account_id, String client_login);
}