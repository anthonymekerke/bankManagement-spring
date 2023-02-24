package bankManagement.accountService.rest_webservice;

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

import bankManagement.accountService.business.dto.AccountBasicDTO;
import bankManagement.accountService.business.dto.TransactionBasicDTO;
import bankManagement.accountService.business.service.IAccountService;
import bankManagement.accountService.util.AppConstants;

@RestController
public class AccountController {

    private IAccountService accountService;

    public AccountController(
        @Qualifier(AppConstants.CORE_ACCOUNT_TYPE) IAccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{ac_id}/transactions/{tr_id}")
    public TransactionBasicDTO getTransactionId(@PathVariable("ac_id") int ac_id, @PathVariable("tr_id")int tr_id, Authentication authentication) {
        return accountService.readTransactionByIdAndClientLogin(ac_id, tr_id, authentication.getName());
    }

    @PostMapping("/accounts/{id}/transactions")
    public ResponseEntity<?> postTransaction(@Valid @RequestBody TransactionBasicDTO dto, @PathVariable("id") int account_id, Authentication authentication) {
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
    public List<TransactionBasicDTO> getAccountsIdTransactions(@PathVariable(value="id") int id, Authentication authentication){
        return this.accountService.readTransactionByAccountIdAndClientLogin(id, authentication.getName());
    }

    @GetMapping("/accounts/{id}")
    public AccountBasicDTO getAccountsId(@PathVariable(value = "id") int id, Authentication authentication){
        return this.accountService.readByIdAndClientLogin(id, authentication.getName());
    }

    @GetMapping("/accounts")
    public List<AccountBasicDTO> getAccounts(Authentication authentication){
        return this.accountService.readByClientLogin(authentication.getName());
    }
}
