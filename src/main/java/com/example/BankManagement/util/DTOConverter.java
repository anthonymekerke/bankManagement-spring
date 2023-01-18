package com.example.BankManagement.util;

import org.springframework.beans.BeanUtils;

import com.example.BankManagement.business.dto.AccountBasicDTO;
import com.example.BankManagement.business.dto.CurrentAccountBasicDTO;
import com.example.BankManagement.business.dto.SavingAccountBasicDTO;
import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.dto.TransactionFullDTO;
import com.example.BankManagement.business.entity.Account;
import com.example.BankManagement.business.entity.Transaction;

public class DTOConverter {
    
    public static Account AccountBasicDTOtoEntity(AccountBasicDTO dto){
        Account entity = new Account();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private static AccountBasicDTO AccountEntitytoBasicDTO(Account entity, AccountBasicDTO dto, String accountType, float balance){
        BeanUtils.copyProperties(entity, dto);
        dto.setAccountType(accountType);
        dto.setBalance(balance);
        return dto;
    }

    public static AccountBasicDTO CoreAccountEntitytoBasicDTO(Account entity, String accountType, float balance){
        AccountBasicDTO dto = new AccountBasicDTO();
        return AccountEntitytoBasicDTO(entity, dto, accountType, balance);
    }

    public static AccountBasicDTO SavingAccountEntitytoBasicDTO(Account entity, float balance){
        AccountBasicDTO dto = new SavingAccountBasicDTO();
        return AccountEntitytoBasicDTO(entity, dto, AppConstants.SAVING_ACCOUNT_TYPE, balance);
    }

    public static AccountBasicDTO CurrentAccountEntitytoBasicDTO(Account entity, float balance){
        AccountBasicDTO dto = new CurrentAccountBasicDTO();
        return AccountEntitytoBasicDTO(entity, dto, AppConstants.CURRENT_ACCOUNT_TYPE, balance);
    }

    public static TransactionBasicDTO TransactionEntitytoBasicDTO(Transaction entity){
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

    public static Transaction TransactionFullDTOtoEntity(TransactionFullDTO dto){
        Transaction entity = new Transaction();
        Account account = AccountBasicDTOtoEntity(dto.getAccount());

        entity.setAccount(account);
        entity.setBalance(dto.getBalance());
        entity.setExecutionDate(dto.getExecutionDate());
        entity.setValueDate(dto.getValueDate());
        entity.setWording(dto.getWording());

        if(dto.getPayment() != null){entity.setPayment(dto.getPayment());}
        if(dto.getWithdraw() != null){entity.setWithdraw(dto.getWithdraw());}

        return entity;
    }
}
