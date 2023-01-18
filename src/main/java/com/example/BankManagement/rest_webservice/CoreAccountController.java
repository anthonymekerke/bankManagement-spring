package com.example.BankManagement.rest_webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankManagement.business.dto.AccountBasicDTO;
import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.service.IAccountService;
import com.example.BankManagement.util.AppConstants;

/*
 * /accounts?client_id={connected user id} -> show the accounts of the connected user
 * /accounts/{id} -> show the account with basic attributes
 * /accounts/{id}/transactions -> show transactions of the account
*/
@RestController
@RequestMapping(value="/accounts")
public class CoreAccountController {

    @Autowired
    @Qualifier(AppConstants.CORE_ACCOUNT_TYPE)
    private IAccountService accountService;

    @GetMapping("/{id}/transactions")
    public List<TransactionBasicDTO> getAccountsIdTransactions(@PathVariable(value="id") int id){
        return this.accountService.readCurrentMonthTransactions(id);
    }

    @GetMapping("/{id}")
    public AccountBasicDTO getAccountsId(@PathVariable(value = "id") int id){
        return this.accountService.readById(id);
    }

    @GetMapping
    public List<AccountBasicDTO> getAccountsByClientId(@RequestParam(name="client_id") int client_id){
        return this.accountService.readByClientId(client_id);
    }
}
