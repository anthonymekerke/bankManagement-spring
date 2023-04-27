package bankManagement.accountService.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import bankManagement.accountService.domain.Account;
import bankManagement.accountService.domain.AccountBasicDTO;
import bankManagement.accountService.exception.UnauthorizedException;
import bankManagement.accountService.repository.IAccountRepository;
import bankManagement.accountService.repository.ICurrentAccountRepository;
import bankManagement.accountService.repository.ISavingAccountRepository;
import bankManagement.accountService.repository.ITransactionRepository;
import bankManagement.accountService.util.AppConstants;
import bankManagement.accountService.util.DTOConverter;

@Service
@Qualifier(AppConstants.CURRENT_ACCOUNT_TYPE)
public class CurrentAccountServiceImpl extends AccountServiceImpl{
    
    public CurrentAccountServiceImpl(IAccountRepository accountRepository,
            ISavingAccountRepository savingAccountRepository, ICurrentAccountRepository currentAccountRepository,
            ITransactionRepository transactionRepository) {
        super(accountRepository, savingAccountRepository, currentAccountRepository, transactionRepository);
    }

    @Override
    public AccountBasicDTO readByIdAndClientLogin(int account_id, String client_login) throws UnauthorizedException{
        Account entity = currentAccountRepository.findByIdAndClient_Login(account_id, client_login).orElse(null);
        if(entity == null){throw new UnauthorizedException("The Current Account n°" + account_id + " is not owned by this client.");}
        return DTOConverter.CurrentAccountEntitytoBasicDTO(entity, readBalance(account_id));
    }
}
