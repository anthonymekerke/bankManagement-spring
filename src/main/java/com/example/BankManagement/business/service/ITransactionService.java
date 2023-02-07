package com.example.BankManagement.business.service;

import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.dto.TransactionFullDTO;
import com.example.BankManagement.exception.UnauthorizedException;

public interface ITransactionService {
    TransactionBasicDTO readByIdAndClientLogin(int tr_id, String client_login) throws UnauthorizedException;
    TransactionFullDTO create(TransactionFullDTO dto, String client_login) throws UnauthorizedException;
}