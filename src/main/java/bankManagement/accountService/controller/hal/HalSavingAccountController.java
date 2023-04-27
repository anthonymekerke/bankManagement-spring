package bankManagement.accountService.controller.hal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bankManagement.accountService.controller.hal.modelAssembler.SavingAccountModelAssembler;
import bankManagement.accountService.domain.SavingAccountBasicDTO;
import bankManagement.accountService.service.IAccountService;
import bankManagement.accountService.util.AppConstants;

@RestController
@RequestMapping("/HAL")
public class HalSavingAccountController {

    private IAccountService accountService;
    private SavingAccountModelAssembler modelAssembler;

    public HalSavingAccountController(
        @Qualifier(AppConstants.SAVING_ACCOUNT_TYPE) IAccountService accountService,
        SavingAccountModelAssembler modelAssembler){
        this.accountService = accountService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping("/saving-accounts/{id}")
    public EntityModel<SavingAccountBasicDTO> getCurrentAccountsId(@PathVariable(value="id")int id,Authentication authentication){
        SavingAccountBasicDTO dto = (SavingAccountBasicDTO)accountService.readByIdAndClientLogin(id, authentication.getName());
        modelAssembler.setAuthentication(authentication);
        return modelAssembler.toModel(dto); 
    }
}