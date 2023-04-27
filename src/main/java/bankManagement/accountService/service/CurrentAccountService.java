package bankManagement.accountService.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import bankManagement.accountService.domain.Account;
import bankManagement.accountService.domain.AccountResponse;
import bankManagement.accountService.exception.UnauthorizedException;
import bankManagement.accountService.repository.AccountRepository;
import bankManagement.accountService.repository.CurrentAccountRepository;
import bankManagement.accountService.repository.SavingAccountRepository;
import bankManagement.accountService.repository.TransactionRepository;
import bankManagement.accountService.util.AppConstants;
import bankManagement.accountService.util.DTOConverter;

@Service
@Qualifier(AppConstants.CURRENT_ACCOUNT_TYPE)
public class CurrentAccountService extends AccountService{
    
    public CurrentAccountService(AccountRepository accountRepository,
            SavingAccountRepository savingAccountRepository, CurrentAccountRepository currentAccountRepository,
            TransactionRepository transactionRepository) {
        super(accountRepository, savingAccountRepository, currentAccountRepository, transactionRepository);
    }

    @Override
    public AccountResponse readByIdAndClientLogin(int account_id, String client_login) throws UnauthorizedException{
        Account entity = currentAccountRepository.findByIdAndClient_Login(account_id, client_login).orElse(null);
        if(entity == null){throw new UnauthorizedException("The Current Account n°" + account_id + " is not owned by this client.");}
        return DTOConverter.CurrentAccountEntitytoBasicDTO(entity, readBalance(account_id));
    }
}
