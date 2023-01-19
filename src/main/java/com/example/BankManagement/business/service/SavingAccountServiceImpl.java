package com.example.BankManagement.business.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.BankManagement.business.dto.AccountBasicDTO;
import com.example.BankManagement.business.entity.Account;
import com.example.BankManagement.util.AppConstants;
import com.example.BankManagement.util.DTOConverter;

@Service
@Qualifier(AppConstants.SAVING_ACCOUNT_TYPE)
public class SavingAccountServiceImpl extends CoreAccountServiceImpl{

    @Override
    public AccountBasicDTO readByIdAndClientLogin(int account_id, String client_login){
        Account entity;
        entity = savingAccountRepository.findByIdAndClient_Login(account_id, client_login).orElse(null);
        if(entity == null){return null;}
        return DTOConverter.SavingAccountEntitytoBasicDTO(entity, readBalance(account_id));
    }
}
