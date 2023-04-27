package bankManagement.accountService.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import bankManagement.accountService.domain.CurrentAccountResponse;
import bankManagement.accountService.service.AccountService;
import bankManagement.accountService.util.AppConstants;

@RestController
public class CurrentAccountController {

    private AccountService accountService;

    public CurrentAccountController(
        @Qualifier(AppConstants.CURRENT_ACCOUNT_TYPE) AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/current-accounts/{id}")
    public CurrentAccountResponse getCurrentAccountsId(@PathVariable(value="id")int id,Authentication authentication){
        return (CurrentAccountResponse)accountService.readByIdAndClientLogin(id, authentication.getName());
    }
}
