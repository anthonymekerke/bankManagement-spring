package com.example.BankManagement.rest_webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankManagement.business.dto.CurrentAccountBasicDTO;
import com.example.BankManagement.business.service.IAccountService;
import com.example.BankManagement.util.AppConstants;

/*
 * /current-accounts/{id} -> show details of the current account
 */
@RestController
@RequestMapping(value = "/current-accounts")
public class CurrentAccountController {
    
    @Autowired
    @Qualifier(AppConstants.CURRENT_ACCOUNT_TYPE)
    private IAccountService accountService;

    @GetMapping("/{id}")
    public CurrentAccountBasicDTO getCurrentAccountsId(@PathVariable(value="id")int id){
        return (CurrentAccountBasicDTO)accountService.readById(id);
    }
}
