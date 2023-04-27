package bankManagement.accountService.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bankManagement.accountService.domain.Account;
import bankManagement.accountService.domain.AccountResponse;
import bankManagement.accountService.domain.CurrentAccountResponse;
import bankManagement.accountService.domain.SavingAccountResponse;
import bankManagement.accountService.domain.Transaction;
import bankManagement.accountService.domain.TransactionResponse;
import bankManagement.accountService.exception.InternalServerErrorException;
import bankManagement.accountService.exception.NotFoundException;
import bankManagement.accountService.exception.UnauthorizedException;
import bankManagement.accountService.repository.AccountRepository;
import bankManagement.accountService.repository.CurrentAccountRepository;
import bankManagement.accountService.repository.SavingAccountRepository;
import bankManagement.accountService.repository.TransactionRepository;
import bankManagement.accountService.util.AppConstants;
import bankManagement.accountService.util.DTOConverter;

@Service
public class AccountService {

    protected AccountRepository accountRepository;
    protected SavingAccountRepository savingAccountRepository;
    protected CurrentAccountRepository currentAccountRepository;
    protected TransactionRepository transactionRepository;

    public AccountService(
        AccountRepository accountRepository,
        SavingAccountRepository savingAccountRepository,
        CurrentAccountRepository currentAccountRepository,
        TransactionRepository transactionRepository
    ) {
        this.accountRepository = accountRepository;
        this.currentAccountRepository = currentAccountRepository;
        this.savingAccountRepository = savingAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    public AccountResponse readByIdAndClientLogin(int account_id, String client_login) throws UnauthorizedException{
        Account entity = accountRepository.findByIdAndClient_Login(account_id, client_login).orElse(null);
        if(entity == null){throw new UnauthorizedException("Account n°" + account_id + " not owned by this client.");}
        return DTOConverter.CoreAccountEntitytoBasicDTO(entity, readAccountTypeById(account_id), readBalance(account_id));
    }

    public CurrentAccountResponse readCurrentAccountByIdAndClientLogin(int account_id, String client_login) throws UnauthorizedException{
        Account entity = currentAccountRepository.findByIdAndClient_Login(account_id, client_login).orElse(null);
        if(entity == null){throw new UnauthorizedException("The Current Account n°" + account_id + " is not owned by this client.");}
        return DTOConverter.CurrentAccountEntitytoBasicDTO(entity, readBalance(account_id));
    }

    public SavingAccountResponse readSavingAccountByIdAndClientLogin(int account_id, String client_login) throws UnauthorizedException{
        Account entity = savingAccountRepository.findByIdAndClient_Login(account_id, client_login).orElse(null);
        if(entity == null){throw new UnauthorizedException("The Saving Accounts n°"+account_id+" is not owned by this client.");}
        return DTOConverter.SavingAccountEntitytoBasicDTO(entity, readBalance(account_id));
    }

    public List<AccountResponse> readByClientLogin(String client_login) throws NotFoundException{
        List<Account> entities = accountRepository.findByClient_Login(client_login);
        if(entities.size() == 0){throw new NotFoundException("No Accounts found for this Client");}
        return entities.stream()
                .map(entity -> DTOConverter.CoreAccountEntitytoBasicDTO(entity, readAccountTypeById(entity.getId()), readBalance(entity.getId())))
                .collect(Collectors.toList());
    }

    public List<TransactionResponse> readTransactionByAccountIdAndClientLogin(int account_id, String client_login) throws NotFoundException{
        List<Transaction> entities = transactionRepository.findByAccount_IdAndAccount_Client_Login(account_id, client_login);
        if(entities.size() == 0){throw new NotFoundException("No transactions found for this Account");}
        return entities.stream()
                .map(entity -> DTOConverter.EntitytoBasicDTO(entity))
                .collect(Collectors.toList());
    }

    public TransactionResponse readTransactionByIdAndClientLogin(int account_id, int transaction_id,
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
    public TransactionResponse createTransactionByAccountIdAndClientLogin(TransactionResponse dto, int account_id,
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
