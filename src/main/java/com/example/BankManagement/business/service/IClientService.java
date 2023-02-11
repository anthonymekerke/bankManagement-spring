package com.example.BankManagement.business.service;

import com.example.BankManagement.business.dto.ClientBasicDTO;
import com.example.BankManagement.exception.NotFoundException;

public interface IClientService {
    ClientBasicDTO readClientByLogin(String client_login) throws NotFoundException;
}
