package bankManagement.accountService.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bankManagement.accountService.domain.ClientBasicDTO;
import bankManagement.accountService.service.IClientService;

@RestController
public class ClientController {
    
    IClientService clientService;

    public ClientController(IClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ClientBasicDTO getClients(Authentication authentication){
        return clientService.readClientByLogin(authentication.getName());
    }
}
