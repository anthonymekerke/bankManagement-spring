package bankManagement.accountService.business.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import bankManagement.accountService.business.dto.AccountBasicDTO;
import bankManagement.accountService.business.entity.Account;
import bankManagement.accountService.business.repository.IAccountRepository;
import bankManagement.accountService.business.repository.ICurrentAccountRepository;
import bankManagement.accountService.business.repository.ISavingAccountRepository;
import bankManagement.accountService.business.repository.ITransactionRepository;
import bankManagement.accountService.exception.UnauthorizedException;
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
