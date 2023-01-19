package com.example.BankManagement.rest_webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankManagement.business.dto.ClientBasicDTO;
import com.example.BankManagement.business.service.IClientService;
import com.example.BankManagement.exception.BankManagementBusinessException;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    @Autowired
    IClientService clientService;

    @GetMapping
    public ClientBasicDTO getClients(Authentication authentication){
        try{
            return clientService.readClientByLogin(authentication.getName());
        }catch(BankManagementBusinessException e){
            /*
             * handle the case when nothing is return with ResponseEntity.
             */
            return null;
        }
    }

    /*
     * Maybe add a PUT method to update 'email' and 'password' field ?
     */
}
