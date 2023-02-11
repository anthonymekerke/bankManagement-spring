package com.example.BankManagement.business.service;

import java.util.List;

import com.example.BankManagement.business.dto.AccountBasicDTO;
import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.dto.TransactionFullDTO;
import com.example.BankManagement.exception.NotFoundException;
import com.example.BankManagement.exception.UnauthorizedException;

public interface IAccountService {
    TransactionBasicDTO readTransactionByIdAndClientLogin(int account_id, int transaction_id, String client_login) throws UnauthorizedException;
    List<TransactionBasicDTO> readTransactionByAccountIdAndClientLogin(int account_id, String client_login) throws NotFoundException;
    TransactionFullDTO createTransactionByAccountIdAndClientLogin(TransactionFullDTO dto, int account_id, String client_login) throws UnauthorizedException;

    AccountBasicDTO readByIdAndClientLogin(int account_id, String client_login) throws UnauthorizedException;
    List<AccountBasicDTO> readByClientLogin(String client_login) throws NotFoundException;   
}
