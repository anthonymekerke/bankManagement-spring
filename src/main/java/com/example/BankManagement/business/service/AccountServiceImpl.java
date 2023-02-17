package com.example.BankManagement.business.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.example.BankManagement.exception.InternalServerErrorException;
import com.example.BankManagement.exception.NotFoundException;
import com.example.BankManagement.exception.UnauthorizedException;
import com.example.BankManagement.util.AppConstants;
import com.example.BankManagement.util.DTOConverter;

@Service
@Qualifier(AppConstants.CORE_ACCOUNT_TYPE)
public class AccountServiceImpl implements IAccountService{

    protected IAccountRepository accountRepository;
    protected ISavingAccountRepository savingAccountRepository;
    protected ICurrentAccountRepository currentAccountRepository;
    protected ITransactionRepository transactionRepository;

    public AccountServiceImpl(IAccountRepository accountRepository,
        ISavingAccountRepository savingAccountRepository,
        ICurrentAccountRepository currentAccountRepository,
        ITransactionRepository transactionRepository){
            this.accountRepository = accountRepository;
            this.currentAccountRepository = currentAccountRepository;
            this.savingAccountRepository = savingAccountRepository;
            this.transactionRepository = transactionRepository;
        }

    @Override
    public AccountBasicDTO readByIdAndClientLogin(int account_id, String client_login) throws UnauthorizedException{
        Account entity = accountRepository.findByIdAndClient_Login(account_id, client_login).orElse(null);
        if(entity == null){throw new UnauthorizedException("Account n°" + account_id + " not owned by this client.");}
        return DTOConverter.CoreAccountEntitytoBasicDTO(entity, readAccountTypeById(account_id), readBalance(account_id));
    }

    @Override
    public List<AccountBasicDTO> readByClientLogin(String client_login) throws NotFoundException{
        List<Account> entities = accountRepository.findByClient_Login(client_login);
        if(entities.size() == 0){throw new NotFoundException("No Accounts found for this Client");}
        return entities.stream()
                .map(entity -> DTOConverter.CoreAccountEntitytoBasicDTO(entity, readAccountTypeById(entity.getId()), readBalance(entity.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionBasicDTO> readTransactionByAccountIdAndClientLogin(int account_id, String client_login) throws NotFoundException{
        List<Transaction> entities = transactionRepository.findByAccount_IdAndAccount_Client_Login(account_id, client_login);
        if(entities.size() == 0){throw new NotFoundException("No transactions found for this Account");}
        return entities.stream()
                .map(entity -> DTOConverter.EntitytoBasicDTO(entity))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionBasicDTO readTransactionByIdAndClientLogin(int account_id, int transaction_id,
            String client_login) throws UnauthorizedException {

        Transaction entity = transactionRepository.findByIdAndAccount_IdAndAccount_Client_Login(transaction_id, account_id, client_login).orElse(null);
        if(entity == null) {throw new UnauthorizedException("The Transaction["+transaction_id+"] is not owned by this client or doesn't exist");}
        return DTOConverter.EntitytoBasicDTO(entity);
    }

    /*
     * When value date is in future, the balance is set to 'null'.
     * 
     * TODO: Find a way to trigger computing of Transaction balance when value date is passed.
     *  - Maybe use a microservice to handle future transaction ? 
     */
    @Override
    public TransactionBasicDTO createTransactionByAccountIdAndClientLogin(TransactionBasicDTO dto, int account_id,
            String client_login) throws UnauthorizedException {
        
        Transaction entity = getLastTransactionByAccountId(account_id);
        float prevBalance = 0.0f, nextBalance;

        ///////////////////////////////////////////////
        // Check if connected client own the account //
        ///////////////////////////////////////////////
        Optional<Account> account = accountRepository.findByIdAndClient_Login(account_id, client_login);
        if(account.isEmpty()){
            throw new UnauthorizedException("The Account["+account_id+"] is not owned by connected client");
        }

        /////////////////////
        // Compute balance //
        /////////////////////
        if(entity != null){prevBalance = entity.getBalance();}
        nextBalance = dto.getPayment() != null ? prevBalance + dto.getPayment() : prevBalance - dto.getWithdraw();

        /////////////////////////////////
        // Set the given 'null' fields //
        /////////////////////////////////
        if(dto.getValueDate() != null){
            dto.setBalance(null);
        }

        if(dto.getValueDate() == null){
            dto.setBalance(nextBalance);
            dto.setValueDate(new Date());
        }

        dto.setExecutionDate(new Date());

        ///////////////////////////////////////////
        // convert DTO to entity & save it in DB //
        ///////////////////////////////////////////
        entity = DTOConverter.BasicDTOtoEntity(dto);
        entity.setAccount(account.get());
        entity = this.transactionRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    private String readAccountTypeById(int account_id) throws InternalServerErrorException{
        if(savingAccountRepository.existsById(account_id)){
            return AppConstants.SAVING_ACCOUNT_TYPE;
        }

        if(currentAccountRepository.existsById(account_id)){
            return AppConstants.CURRENT_ACCOUNT_TYPE;
        }

        throw new InternalServerErrorException("Internal error with an unknown Account type");
    }

    private Transaction getLastTransactionByAccountId(int account_id){
        return transactionRepository.findFirstByAccountIdAndBalanceNotNullOrderByValueDateDesc(account_id).orElse(null);
    }

    protected float readBalance(int account_id){
        Transaction last = getLastTransactionByAccountId(account_id);
        return last == null ? 0.0f : last.getBalance();
    }
}
