package bankManagement.accountService.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import bankManagement.accountService.domain.SavingAccountResponse;
import bankManagement.accountService.service.AccountService;
import bankManagement.accountService.util.AppConstants;

@RestController
public class SavingAccountController {

    private AccountService accountService;

    public SavingAccountController(
        @Qualifier(AppConstants.SAVING_ACCOUNT_TYPE) AccountService accountService
    ) {
        this.accountService = accountService;
    }

    @GetMapping("/saving-accounts/{id}")
    public SavingAccountResponse getSavingAccountsId(@PathVariable(value="id") int id, Authentication authentication){
        return (SavingAccountResponse)accountService.readByIdAndClientLogin(id, authentication.getName());
    }
}
