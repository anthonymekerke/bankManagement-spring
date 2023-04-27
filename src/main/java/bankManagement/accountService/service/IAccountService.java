package bankManagement.accountService.service;

import java.util.List;

import bankManagement.accountService.domain.AccountBasicDTO;
import bankManagement.accountService.domain.TransactionBasicDTO;
import bankManagement.accountService.exception.NotFoundException;
import bankManagement.accountService.exception.UnauthorizedException;

public interface IAccountService {
    TransactionBasicDTO readTransactionByIdAndClientLogin(int account_id, int transaction_id, String client_login) throws UnauthorizedException;
    List<TransactionBasicDTO> readTransactionByAccountIdAndClientLogin(int account_id, String client_login) throws NotFoundException;
    TransactionBasicDTO createTransactionByAccountIdAndClientLogin(TransactionBasicDTO dto, int account_id, String client_login) throws UnauthorizedException;

    AccountBasicDTO readByIdAndClientLogin(int account_id, String client_login) throws UnauthorizedException;
    List<AccountBasicDTO> readByClientLogin(String client_login) throws NotFoundException;
}
