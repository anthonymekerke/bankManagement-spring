package bankManagement.accountService.service;

import org.springframework.stereotype.Service;

import bankManagement.accountService.domain.Client;
import bankManagement.accountService.domain.ClientResponse;
import bankManagement.accountService.exception.NotFoundException;
import bankManagement.accountService.repository.ClientRepository;
import bankManagement.accountService.util.DTOConverter;

@Service
public class ClientService {

    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientResponse readClientByLogin(String client_login) throws NotFoundException{
        Client entity = clientRepository.findByLogin(client_login).orElse(null);
        if(entity == null) {throw new NotFoundException("Erreur normalement impossible!");}
        return DTOConverter.EntitytoBasicDTO(entity);
    }
    
}
