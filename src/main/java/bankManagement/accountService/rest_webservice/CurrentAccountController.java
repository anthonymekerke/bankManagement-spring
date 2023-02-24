package bankManagement.accountService.rest_webservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import bankManagement.accountService.business.dto.CurrentAccountBasicDTO;
import bankManagement.accountService.business.service.IAccountService;
import bankManagement.accountService.util.AppConstants;

@RestController
public class CurrentAccountController {

    private IAccountService accountService;

    public CurrentAccountController(
        @Qualifier(AppConstants.CURRENT_ACCOUNT_TYPE) IAccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/current-accounts/{id}")
    public CurrentAccountBasicDTO getCurrentAccountsId(@PathVariable(value="id")int id,Authentication authentication){
        return (CurrentAccountBasicDTO)accountService.readByIdAndClientLogin(id, authentication.getName());
    }
}
