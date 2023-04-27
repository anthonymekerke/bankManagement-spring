package bankManagement.accountService.controller.hal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import bankManagement.accountService.controller.hal.modelAssembler.AccountModelAssembler;
import bankManagement.accountService.controller.hal.modelAssembler.TransactionModelAssembler;
import bankManagement.accountService.domain.AccountResponse;
import bankManagement.accountService.domain.TransactionResponse;
import bankManagement.accountService.service.AccountService;
import bankManagement.accountService.util.AppConstants;

@RestController
@RequestMapping("/HAL")
public class HalAccountController {

    private AccountService accountService;
    private TransactionModelAssembler transactionAssembler;
    private AccountModelAssembler accountModelAssembler;

    public HalAccountController(
        @Qualifier(AppConstants.CORE_ACCOUNT_TYPE) AccountService accountService,
        TransactionModelAssembler transactionModelAssembler,
        AccountModelAssembler accountModelAssembler
    ) {
        this.accountModelAssembler = accountModelAssembler;
        this.accountService = accountService;
        this.transactionAssembler = transactionModelAssembler;
    }

    @GetMapping("/accounts/{ac_id}/transactions/{tr_id}")
    public EntityModel<TransactionResponse> getTransactionId(@PathVariable("ac_id") int ac_id, @PathVariable("tr_id")int tr_id, Authentication authentication) {
        TransactionResponse dto = accountService.readTransactionByIdAndClientLogin(ac_id, tr_id, authentication.getName());
        transactionAssembler.setAccountId(ac_id);
        transactionAssembler.setAuthentication(authentication);
        return transactionAssembler.toModel(dto);
    }

    @GetMapping("/accounts/{id}/transactions")
    public CollectionModel<EntityModel<TransactionResponse>> getAccountsIdTransactions(@PathVariable(value="id") int id, Authentication authentication){
        transactionAssembler.setAccountId(id);
        transactionAssembler.setAuthentication(authentication);
        
        List<EntityModel<TransactionResponse>> transactions = accountService.readTransactionByAccountIdAndClientLogin(id, authentication.getName())
        .stream()
        .map(transactionAssembler::toModel)
        .collect(Collectors.toList());
        
        return CollectionModel.of(transactions, linkTo(methodOn(HalAccountController.class).getAccountsIdTransactions(id, authentication)).withSelfRel());
    }

    /*
     * TODO: Maybe separate payment & withdraw into 2 separate endpoints to
     * allow hypermedia interaction, see: https://en.wikipedia.org/wiki/HATEOAS
     */
    @PostMapping("/accounts/{id}/transactions")
    public ResponseEntity<?> postTransaction(@Valid @RequestBody TransactionResponse dto, @PathVariable("id") int account_id, Authentication authentication) {
        dto = accountService.createTransactionByAccountIdAndClientLogin(dto, account_id, authentication.getName());
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(dto.getId())
            .toUri(); 
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/accounts/{id}")
    public EntityModel<AccountResponse> getAccountsId(@PathVariable(value = "id") int id, Authentication authentication){
        AccountResponse dto = accountService.readByIdAndClientLogin(id, authentication.getName());
        accountModelAssembler.setAuthentication(authentication);
        return accountModelAssembler.toModel(dto);
    }

    @GetMapping("/accounts")
    public CollectionModel<EntityModel<AccountResponse>> getAccounts(Authentication authentication){
        accountModelAssembler.setAuthentication(authentication);

        List<EntityModel<AccountResponse>> accounts = accountService.readByClientLogin(authentication.getName())
        .stream()
        .map(accountModelAssembler::toModel)
        .collect(Collectors.toList());

        return CollectionModel.of(accounts, linkTo(methodOn(HalAccountController.class).getAccounts(authentication)).withSelfRel());
    }
}
