package com.example.BankManagement.business.service;

import java.util.List;

import com.example.BankManagement.business.dto.AccountBasicDTO;
import com.example.BankManagement.business.dto.TransactionBasicDTO;

public interface IAccountService {
    AccountBasicDTO readById(int account_id);
    List<AccountBasicDTO> readByClientLogin(String client_login);
    List<TransactionBasicDTO> readCurrentMonthTransactions(int account_id);
}
