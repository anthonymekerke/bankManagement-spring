package bankManagement.accountService.business.service;

import java.util.List;

import bankManagement.accountService.business.dto.AccountBasicDTO;
import bankManagement.accountService.business.dto.TransactionBasicDTO;
import bankManagement.accountService.exception.NotFoundException;
import bankManagement.accountService.exception.UnauthorizedException;

public interface IAccountService {
    TransactionBasicDTO readTransactionByIdAndClientLogin(int account_id, int transaction_id, String client_login) throws UnauthorizedException;
    List<TransactionBasicDTO> readTransactionByAccountIdAndClientLogin(int account_id, String client_login) throws NotFoundException;
    TransactionBasicDTO createTransactionByAccountIdAndClientLogin(TransactionBasicDTO dto, int account_id, String client_login) throws UnauthorizedException;

    AccountBasicDTO readByIdAndClientLogin(int account_id, String client_login) throws UnauthorizedException;
    List<AccountBasicDTO> readByClientLogin(String client_login) throws NotFoundException;
}
