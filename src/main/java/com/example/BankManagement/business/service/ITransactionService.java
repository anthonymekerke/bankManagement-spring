package com.example.BankManagement.business.service;

import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.dto.TransactionFullDTO;
import com.example.BankManagement.exception.BankManagementBusinessException;

public interface ITransactionService {
    TransactionBasicDTO readByIdAndClientLogin(int tr_id, String client_login) throws BankManagementBusinessException;
    TransactionFullDTO create(TransactionFullDTO dto) throws BankManagementBusinessException;
}