package com.example.BankManagement.business.service;

import java.util.List;

import com.example.BankManagement.business.dto.AccountBasicDTO;
import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.exception.BankManagementBusinessException;

public interface IAccountService {
    List<TransactionBasicDTO> readCurrentMonthTransactions(int account_id);

    AccountBasicDTO readByIdAndClientLogin(int account_id, String client_login) throws BankManagementBusinessException;
    List<AccountBasicDTO> readByClientLogin(String client_login) throws BankManagementBusinessException;
    List<TransactionBasicDTO> readTransactionByAccountIdAndClientLogin(int account_id, String client_login) throws BankManagementBusinessException;   
}
