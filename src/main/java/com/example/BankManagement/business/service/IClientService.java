package com.example.BankManagement.business.service;

import com.example.BankManagement.business.dto.ClientBasicDTO;
import com.example.BankManagement.business.dto.ClientFullDTO;
import com.example.BankManagement.business.dto.ClientMediumDTO;
import com.example.BankManagement.business.entity.Client;
import com.example.BankManagement.business.repository.IClientRepository;

public interface IClientService extends 
    IService<Client, ClientBasicDTO, ClientMediumDTO, ClientFullDTO, IClientRepository> {
    
}
