package com.example.BankManagement.rest_webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankManagement.business.dto.SavingAccountBasicDTO;
import com.example.BankManagement.business.service.IAccountService;
import com.example.BankManagement.util.AppConstants;

@RestController
public class SavingAccountController {

    @Autowired
    @Qualifier(AppConstants.SAVING_ACCOUNT_TYPE)
    private IAccountService accountService;

    @GetMapping("/saving-accounts/{id}")
    public SavingAccountBasicDTO getSavingAccountsId(@PathVariable(value="id") int id, Authentication authentication){
        return (SavingAccountBasicDTO)accountService.readByIdAndClientLogin(id, authentication.getName());
    }
}
