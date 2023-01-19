package com.example.BankManagement.rest_webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankManagement.business.dto.AccountBasicDTO;
import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.service.IAccountService;
import com.example.BankManagement.exception.BankManagementBusinessException;
import com.example.BankManagement.util.AppConstants;

/*
 * /accounts -> show the accounts of connected user
 * /accounts/{id} -> show the account of connected user with basic attributes
 * /accounts/{id}/transactions -> show every transactions of the account
*/
@RestController
@RequestMapping(value="/accounts")
public class CoreAccountController {

    @Autowired
    @Qualifier(AppConstants.CORE_ACCOUNT_TYPE)
    private IAccountService accountService;

    @GetMapping("/{id}/transactions")
    public List<TransactionBasicDTO> getAccountsIdTransactions(@PathVariable(value="id") int id, Authentication authentication){
        try {
            return this.accountService.readTransactionByAccountIdAndClientLogin(id, authentication.getName());
        } catch (BankManagementBusinessException e) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public AccountBasicDTO getAccountsId(@PathVariable(value = "id") int id, Authentication authentication){
        try {
            return this.accountService.readByIdAndClientLogin(id, authentication.getName());
        } catch (BankManagementBusinessException e) {
            return null;
        }
    }

    @GetMapping
    public List<AccountBasicDTO> getAccounts(Authentication authentication){
        try {
            return this.accountService.readByClientLogin(authentication.getName());
        } catch (BankManagementBusinessException e) {
            return null;
        }
    }
}
