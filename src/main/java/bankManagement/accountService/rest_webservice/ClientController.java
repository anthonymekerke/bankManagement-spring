package bankManagement.accountService.rest_webservice;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bankManagement.accountService.business.dto.ClientBasicDTO;
import bankManagement.accountService.business.service.IClientService;

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
