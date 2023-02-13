package com.example.BankManagement.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankManagement.business.dto.ClientBasicDTO;
import com.example.BankManagement.business.entity.Client;
import com.example.BankManagement.business.repository.IClientRepository;
import com.example.BankManagement.exception.NotFoundException;
import com.example.BankManagement.util.DTOConverter;

@Service
public class ClientServiceImpl implements IClientService{

    @Autowired
    IClientRepository clientRepository;

    @Override
    public ClientBasicDTO readClientByLogin(String client_login) throws NotFoundException{
        Client entity = clientRepository.findByLogin(client_login).orElse(null);
        if(entity == null) {throw new NotFoundException("Erreur normalement impossible!");}
        return DTOConverter.EntitytoBasicDTO(entity);
    }
    
}
