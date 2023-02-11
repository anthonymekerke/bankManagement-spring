package com.example.BankManagement.business.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.BankManagement.business.dto.AccountBasicDTO;
import com.example.BankManagement.business.entity.Account;
import com.example.BankManagement.exception.UnauthorizedException;
import com.example.BankManagement.util.AppConstants;
import com.example.BankManagement.util.DTOConverter;

@Service
@Qualifier(AppConstants.SAVING_ACCOUNT_TYPE)
public class SavingAccountServiceImpl extends AccountServiceImpl{

    @Override
    public AccountBasicDTO readByIdAndClientLogin(int account_id, String client_login) throws UnauthorizedException{
        Account entity = savingAccountRepository.findByIdAndClient_Login(account_id, client_login).orElse(null);
        if(entity == null){throw new UnauthorizedException("The Saving Accounts n°"+account_id+" is not owned by this client.");}
        return DTOConverter.SavingAccountEntitytoBasicDTO(entity, readBalance(account_id));
    }
}
