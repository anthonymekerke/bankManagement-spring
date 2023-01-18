package com.example.BankManagement.business.service;

import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.dto.TransactionFullDTO;

public interface ITransactionService {
    TransactionBasicDTO readById(int id);
    TransactionFullDTO create(TransactionFullDTO dto);
}