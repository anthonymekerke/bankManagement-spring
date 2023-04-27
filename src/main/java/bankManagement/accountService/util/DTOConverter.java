package bankManagement.accountService.util;

import org.springframework.beans.BeanUtils;

import bankManagement.accountService.domain.Account;
import bankManagement.accountService.domain.AccountBasicDTO;
import bankManagement.accountService.domain.Client;
import bankManagement.accountService.domain.ClientBasicDTO;
import bankManagement.accountService.domain.CurrentAccountBasicDTO;
import bankManagement.accountService.domain.SavingAccountBasicDTO;
import bankManagement.accountService.domain.Transaction;
import bankManagement.accountService.domain.TransactionBasicDTO;

public class DTOConverter {
    
    public static Transaction BasicDTOtoEntity(TransactionBasicDTO dto){
        Transaction entity = new Transaction();

        entity.setBalance(dto.getBalance());
        entity.setExecutionDate(dto.getExecutionDate());
        entity.setValueDate(dto.getValueDate());
        entity.setWording(dto.getWording());

        if(dto.getPayment() != null){entity.setPayment(dto.getPayment());}
        if(dto.getWithdraw() != null){entity.setWithdraw(dto.getWithdraw());}

        return entity;
    }

    public static TransactionBasicDTO EntitytoBasicDTO(Transaction entity){
        TransactionBasicDTO dto = new TransactionBasicDTO();
        
        dto.setBalance(entity.getBalance());
        dto.setExecutionDate(entity.getExecutionDate());
        dto.setValueDate(entity.getValueDate());
        dto.setId(entity.getId());
        dto.setWording(entity.getWording());

        if(entity.getPayment() != null){dto.setPayment(entity.getPayment());}
        if(entity.getWithdraw() != null){dto.setWithdraw(entity.getWithdraw());}
        
        return dto;
    }

    public static ClientBasicDTO EntitytoBasicDTO(Client entity){
        ClientBasicDTO dto = new ClientBasicDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    private static AccountBasicDTO EntitytoBasicDTO(Account entity, AccountBasicDTO dto, String accountType, float balance){
        BeanUtils.copyProperties(entity, dto);
        dto.setAccountType(accountType);
        dto.setBalance(balance);
        return dto;
    }

    public static AccountBasicDTO CoreAccountEntitytoBasicDTO(Account entity, String accountType, float balance){
        AccountBasicDTO dto = new AccountBasicDTO();
        return EntitytoBasicDTO(entity, dto, accountType, balance);
    }

    public static AccountBasicDTO SavingAccountEntitytoBasicDTO(Account entity, float balance){
        AccountBasicDTO dto = new SavingAccountBasicDTO();
        return EntitytoBasicDTO(entity, dto, AppConstants.SAVING_ACCOUNT_TYPE, balance);
    }

    public static AccountBasicDTO CurrentAccountEntitytoBasicDTO(Account entity, float balance){
        AccountBasicDTO dto = new CurrentAccountBasicDTO();
        return EntitytoBasicDTO(entity, dto, AppConstants.CURRENT_ACCOUNT_TYPE, balance);
    }
}
