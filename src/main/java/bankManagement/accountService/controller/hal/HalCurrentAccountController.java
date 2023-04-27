package bankManagement.accountService.controller.hal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bankManagement.accountService.controller.hal.modelAssembler.CurrentAccountModelAssembler;
import bankManagement.accountService.domain.CurrentAccountResponse;
import bankManagement.accountService.service.AccountService;
import bankManagement.accountService.util.AppConstants;

@RestController
@RequestMapping("/HAL")
public class HalCurrentAccountController {

    private AccountService accountService;
    private CurrentAccountModelAssembler modelAssembler;

    public HalCurrentAccountController(
        @Qualifier(AppConstants.CURRENT_ACCOUNT_TYPE) AccountService accountService,
        CurrentAccountModelAssembler modelAssembler
    ) {
        this.accountService = accountService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping("/current-accounts/{id}")
    public EntityModel<CurrentAccountResponse> getCurrentAccountsId(@PathVariable(value="id")int id,Authentication authentication){
        CurrentAccountResponse dto = (CurrentAccountResponse)accountService.readByIdAndClientLogin(id, authentication.getName());
        modelAssembler.setAuthentication(authentication);
        return modelAssembler.toModel(dto); 
    }
}