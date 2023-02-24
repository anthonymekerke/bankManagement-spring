package bankManagement.accountService.rest_webservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import bankManagement.accountService.business.dto.SavingAccountBasicDTO;
import bankManagement.accountService.business.service.IAccountService;
import bankManagement.accountService.util.AppConstants;

@RestController
public class SavingAccountController {

    private IAccountService accountService;

    public SavingAccountController(
        @Qualifier(AppConstants.SAVING_ACCOUNT_TYPE) IAccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/saving-accounts/{id}")
    public SavingAccountBasicDTO getSavingAccountsId(@PathVariable(value="id") int id, Authentication authentication){
        return (SavingAccountBasicDTO)accountService.readByIdAndClientLogin(id, authentication.getName());
    }
}
