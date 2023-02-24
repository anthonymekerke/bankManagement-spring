package bankManagement.accountService.business.service;

import bankManagement.accountService.business.dto.ClientBasicDTO;
import bankManagement.accountService.exception.NotFoundException;

public interface IClientService {
    ClientBasicDTO readClientByLogin(String client_login) throws NotFoundException;
}
