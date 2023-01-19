package com.example.BankManagement.business.service;

import com.example.BankManagement.business.dto.ClientBasicDTO;

public interface IClientService {
    ClientBasicDTO readClientByLogin(String client_login);
}
