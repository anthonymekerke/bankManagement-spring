package com.example.BankManagement.business.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.BankManagement.business.dto.AccountBasicDTO;
import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.entity.Account;
import com.example.BankManagement.business.entity.Transaction;
import com.example.BankManagement.business.repository.IAccountRepository;
import com.example.BankManagement.business.repository.ICurrentAccountRepository;
import com.example.BankManagement.business.repository.ISavingAccountRepository;
import com.example.BankManagement.business.repository.ITransactionRepository;
import com.example.BankManagement.util.AppConstants;
import com.example.BankManagement.util.DTOConverter;

/*
 * WARNING: Since @OneToMany attributes are FetchType.LAZY by default,
 * if you want to get access to those attributes, you got to annotate
 * the method calling the .get<Attributes> with javax.transaction.Transactional
 * in order to read them.
 */
@Service
@Qualifier(AppConstants.CORE_ACCOUNT_TYPE)
public class CoreAccountServiceImpl implements IAccountService{

    @Autowired
    protected IAccountRepository accountRepository;
    
    @Autowired
    protected ISavingAccountRepository savingAccountRepository;

    @Autowired
    protected ICurrentAccountRepository currentAccountRepository;

    @Autowired
    protected ITransactionRepository transactionRepository;

    @Override
    public AccountBasicDTO readByIdAndClientLogin(int account_id, String client_login){
        Account entity = accountRepository.findByIdAndClient_Login(account_id, client_login).orElse(null);

        if(entity == null){return null;}
        return DTOConverter.CoreAccountEntitytoBasicDTO(entity, readAccountType(account_id), readBalance(account_id));
    }

    @Override
    public List<AccountBasicDTO> readByClientLogin(String client_login) {
        List<Account> entities = accountRepository.findByClient_Login(client_login);

        return entities.stream()
                .map(entity -> DTOConverter.CoreAccountEntitytoBasicDTO(entity, readAccountType(entity.getId()), readBalance(entity.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionBasicDTO> readCurrentMonthTransactions(int account_id) {
        /* TODO 
         * This method is supposed to retrieve transactions 
         * of the current month but for now, it retrieves every transactions of the account.
         * This features will be implemented later using Pagination.
        */
        List<Transaction> entities = transactionRepository.findByAccount_Id(account_id);

        return entities.stream()
                .map(entity -> DTOConverter.TransactionEntitytoBasicDTO(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionBasicDTO> readTransactionByAccountIdAndClientLogin(int account_id, String client_login){
        List<Transaction> entities = transactionRepository.findByAccount_IdAndAccount_Client_Login(account_id, client_login);

        return entities.stream()
                .map(entity -> DTOConverter.TransactionEntitytoBasicDTO(entity))
                .collect(Collectors.toList());
    }

    private String readAccountType(int account_id){
        if(savingAccountRepository.existsById(account_id)){
            return AppConstants.SAVING_ACCOUNT_TYPE;
        }

        if(currentAccountRepository.existsById(account_id)){
            return AppConstants.CURRENT_ACCOUNT_TYPE;
        }

        return null;
    }

    protected float readBalance(int account_id){
        return transactionRepository.findFirstByAccountIdOrderByValueDateDesc(account_id).getBalance();
    }
}
