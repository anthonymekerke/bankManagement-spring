package bankManagement.accountService.service;

import bankManagement.accountService.domain.ClientBasicDTO;
import bankManagement.accountService.exception.NotFoundException;

public interface IClientService {
    ClientBasicDTO readClientByLogin(String client_login) throws NotFoundException;
}
