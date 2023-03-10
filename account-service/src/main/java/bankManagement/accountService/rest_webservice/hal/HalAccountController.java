package bankManagement.accountService.rest_webservice.hal;

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

import bankManagement.accountService.business.dto.AccountBasicDTO;
import bankManagement.accountService.business.dto.TransactionBasicDTO;
import bankManagement.accountService.business.service.IAccountService;
import bankManagement.accountService.rest_webservice.hal.modelAssembler.AccountModelAssembler;
import bankManagement.accountService.rest_webservice.hal.modelAssembler.TransactionModelAssembler;
import bankManagement.accountService.util.AppConstants;

@RestController
@RequestMapping("/HAL")
public class HalAccountController {

    private IAccountService accountService;
    private TransactionModelAssembler transactionAssembler;
    private AccountModelAssembler accountModelAssembler;

    public HalAccountController(
        @Qualifier(AppConstants.CORE_ACCOUNT_TYPE) IAccountService accountService,
        TransactionModelAssembler transactionModelAssembler,
        AccountModelAssembler accountModelAssembler){
        this.accountModelAssembler = accountModelAssembler;
        this.accountService = accountService;
        this.transactionAssembler = transactionModelAssembler;
    }

    @GetMapping("/accounts/{ac_id}/transactions/{tr_id}")
    public EntityModel<TransactionBasicDTO> getTransactionId(@PathVariable("ac_id") int ac_id, @PathVariable("tr_id")int tr_id, Authentication authentication) {
        TransactionBasicDTO dto = accountService.readTransactionByIdAndClientLogin(ac_id, tr_id, authentication.getName());
        transactionAssembler.setAccountId(ac_id);
        transactionAssembler.setAuthentication(authentication);
        return transactionAssembler.toModel(dto);
    }

    @GetMapping("/accounts/{id}/transactions")
    public CollectionModel<EntityModel<TransactionBasicDTO>> getAccountsIdTransactions(@PathVariable(value="id") int id, Authentication authentication){
        transactionAssembler.setAccountId(id);
        transactionAssembler.setAuthentication(authentication);
        
        List<EntityModel<TransactionBasicDTO>> transactions = accountService.readTransactionByAccountIdAndClientLogin(id, authentication.getName())
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
    public ResponseEntity<?> postTransaction(@Valid @RequestBody TransactionBasicDTO dto, @PathVariable("id") int account_id, Authentication authentication) {
        dto = accountService.createTransactionByAccountIdAndClientLogin(dto, account_id, authentication.getName());
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(dto.getId())
            .toUri(); 
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/accounts/{id}")
    public EntityModel<AccountBasicDTO> getAccountsId(@PathVariable(value = "id") int id, Authentication authentication){
        AccountBasicDTO dto = accountService.readByIdAndClientLogin(id, authentication.getName());
        accountModelAssembler.setAuthentication(authentication);
        return accountModelAssembler.toModel(dto);
    }

    @GetMapping("/accounts")
    public CollectionModel<EntityModel<AccountBasicDTO>> getAccounts(Authentication authentication){
        accountModelAssembler.setAuthentication(authentication);

        List<EntityModel<AccountBasicDTO>> accounts = accountService.readByClientLogin(authentication.getName())
        .stream()
        .map(accountModelAssembler::toModel)
        .collect(Collectors.toList());

        return CollectionModel.of(accounts, linkTo(methodOn(HalAccountController.class).getAccounts(authentication)).withSelfRel());
    }
}
