package bankManagement.accountService.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import bankManagement.accountService.domain.AccountResponse;
import bankManagement.accountService.domain.TransactionResponse;
import bankManagement.accountService.service.AccountService;
import bankManagement.accountService.util.AppConstants;

@RestController
public class AccountController {

    private AccountService accountService;

    public AccountController(
        @Qualifier(AppConstants.CORE_ACCOUNT_TYPE) AccountService accountService
    ) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{ac_id}/transactions/{tr_id}")
    public TransactionResponse getTransactionId(@PathVariable("ac_id") int ac_id, @PathVariable("tr_id")int tr_id, Authentication authentication) {
        return accountService.readTransactionByIdAndClientLogin(ac_id, tr_id, authentication.getName());
    }

    @PostMapping("/accounts/{id}/transactions")
    public ResponseEntity<?> postTransaction(@Valid @RequestBody TransactionResponse dto, @PathVariable("id") int account_id, Authentication authentication) {
        dto = accountService.createTransactionByAccountIdAndClientLogin(dto, account_id, authentication.getName());

        /*
         * Return a response entity of status 201 (created succesfully).
         * This append the URI of the new resource created (ex: /transactions/5)
         * in the header of the response with the "location" name
         */
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(dto.getId())
            .toUri(); 
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/accounts/{id}/transactions")
    public List<TransactionResponse> getAccountsIdTransactions(@PathVariable(value="id") int id, Authentication authentication){
        return this.accountService.readTransactionByAccountIdAndClientLogin(id, authentication.getName());
    }

    @GetMapping("/accounts/{id}")
    public AccountResponse getAccountsId(@PathVariable(value = "id") int id, Authentication authentication){
        return this.accountService.readByIdAndClientLogin(id, authentication.getName());
    }

    @GetMapping("/accounts")
    public List<AccountResponse> getAccounts(Authentication authentication){
        return this.accountService.readByClientLogin(authentication.getName());
    }
}
