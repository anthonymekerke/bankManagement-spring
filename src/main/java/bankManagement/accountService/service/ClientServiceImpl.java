package bankManagement.accountService.service;

import org.springframework.stereotype.Service;

import bankManagement.accountService.domain.Client;
import bankManagement.accountService.domain.ClientBasicDTO;
import bankManagement.accountService.exception.NotFoundException;
import bankManagement.accountService.repository.IClientRepository;
import bankManagement.accountService.util.DTOConverter;

@Service
public class ClientServiceImpl implements IClientService{

    IClientRepository clientRepository;

    public ClientServiceImpl(IClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientBasicDTO readClientByLogin(String client_login) throws NotFoundException{
        Client entity = clientRepository.findByLogin(client_login).orElse(null);
        if(entity == null) {throw new NotFoundException("Erreur normalement impossible!");}
        return DTOConverter.EntitytoBasicDTO(entity);
    }
    
}
