package bankManagement.accountService.controller.hal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bankManagement.accountService.controller.hal.modelAssembler.SavingAccountModelAssembler;
import bankManagement.accountService.domain.SavingAccountResponse;
import bankManagement.accountService.service.AccountService;
import bankManagement.accountService.util.AppConstants;

@RestController
@RequestMapping("/HAL")
public class HalSavingAccountController {

    private AccountService accountService;
    private SavingAccountModelAssembler modelAssembler;

    public HalSavingAccountController(
        @Qualifier(AppConstants.SAVING_ACCOUNT_TYPE) AccountService accountService,
        SavingAccountModelAssembler modelAssembler
    ) {
        this.accountService = accountService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping("/saving-accounts/{id}")
    public EntityModel<SavingAccountResponse> getCurrentAccountsId(@PathVariable(value="id")int id,Authentication authentication){
        SavingAccountResponse dto = (SavingAccountResponse)accountService.readByIdAndClientLogin(id, authentication.getName());
        modelAssembler.setAuthentication(authentication);
        return modelAssembler.toModel(dto); 
    }
}