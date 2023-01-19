package com.example.BankManagement.business.service;

import com.example.BankManagement.business.dto.ClientBasicDTO;
import com.example.BankManagement.exception.BankManagementBusinessException;

public interface IClientService {
    ClientBasicDTO readClientByLogin(String client_login) throws BankManagementBusinessException;
}
