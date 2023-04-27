package bankManagement.accountService.util;

import org.springframework.beans.BeanUtils;

import bankManagement.accountService.domain.Account;
import bankManagement.accountService.domain.AccountResponse;
import bankManagement.accountService.domain.Client;
import bankManagement.accountService.domain.ClientResponse;
import bankManagement.accountService.domain.CurrentAccountResponse;
import bankManagement.accountService.domain.SavingAccountResponse;
import bankManagement.accountService.domain.Transaction;
import bankManagement.accountService.domain.TransactionResponse;

public class DTOConverter {
    
    public static Transaction BasicDTOtoEntity(TransactionResponse dto){
        Transaction entity = new Transaction();

        entity.setBalance(dto.getBalance());
        entity.setExecutionDate(dto.getExecutionDate());
        entity.setValueDate(dto.getValueDate());
        entity.setWording(dto.getWording());

        if(dto.getPayment() != null){entity.setPayment(dto.getPayment());}
        if(dto.getWithdraw() != null){entity.setWithdraw(dto.getWithdraw());}

        return entity;
    }

    public static TransactionResponse EntitytoBasicDTO(Transaction entity){
        TransactionResponse dto = new TransactionResponse();
        
        dto.setBalance(entity.getBalance());
        dto.setExecutionDate(entity.getExecutionDate());
        dto.setValueDate(entity.getValueDate());
        dto.setId(entity.getId());
        dto.setWording(entity.getWording());

        if(entity.getPayment() != null){dto.setPayment(entity.getPayment());}
        if(entity.getWithdraw() != null){dto.setWithdraw(entity.getWithdraw());}
        
        return dto;
    }

    public static ClientResponse EntitytoBasicDTO(Client entity){
        ClientResponse dto = new ClientResponse();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    private static AccountResponse EntitytoBasicDTO(Account entity, AccountResponse dto, String accountType, float balance){
        BeanUtils.copyProperties(entity, dto);
        dto.setAccountType(accountType);
        dto.setBalance(balance);
        return dto;
    }

    public static AccountResponse CoreAccountEntitytoBasicDTO(Account entity, String accountType, float balance){
        AccountResponse dto = new AccountResponse();
        return EntitytoBasicDTO(entity, dto, accountType, balance);
    }

    public static AccountResponse SavingAccountEntitytoBasicDTO(Account entity, float balance){
        AccountResponse dto = new SavingAccountResponse();
        return EntitytoBasicDTO(entity, dto, AppConstants.SAVING_ACCOUNT_TYPE, balance);
    }

    public static AccountResponse CurrentAccountEntitytoBasicDTO(Account entity, float balance){
        AccountResponse dto = new CurrentAccountResponse();
        return EntitytoBasicDTO(entity, dto, AppConstants.CURRENT_ACCOUNT_TYPE, balance);
    }
}
