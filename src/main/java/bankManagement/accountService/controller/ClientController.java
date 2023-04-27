package bankManagement.accountService.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bankManagement.accountService.domain.ClientResponse;
import bankManagement.accountService.service.ClientService;

@RestController
public class ClientController {
    
    ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ClientResponse getClients(Authentication authentication){
        return clientService.readClientByLogin(authentication.getName());
    }
}
