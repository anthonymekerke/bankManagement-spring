package com.example.BankManagement.rest_webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankManagement.business.dto.SavingAccountBasicDTO;
import com.example.BankManagement.business.service.IAccountService;
import com.example.BankManagement.exception.BankManagementBusinessException;
import com.example.BankManagement.util.AppConstants;

/*
 * /saving-accounts/{id} -> show details of the saving account
 */
@RestController
@RequestMapping(value="/saving-accounts")
public class SavingAccountController {

    @Autowired
    @Qualifier(AppConstants.SAVING_ACCOUNT_TYPE)
    private IAccountService accountService;
    
    /*
     * import javax.validation.Valid;
     * import org.springframework.http.ResponseEntity;
     * 
     * public ResponseEntity<?> createAccount(@Valid @RequestBody GAccountFullDTO dto)
	 *      throws BusinessException {
     *      return ResponseEntity.ok(accountService.create(dto));
	 * }
     */
    /*@PostMapping
    public void postAccounts(@RequestBody AccountMediumDTO dto){
        accountService.create(dto);
    }*/

    @GetMapping("/{id}")
    public SavingAccountBasicDTO getSavingAccountsId(@PathVariable(value="id") int id, Authentication authentication){
        try {
            return (SavingAccountBasicDTO)accountService.readByIdAndClientLogin(id, authentication.getName());
        } catch (BankManagementBusinessException e) {
            return null;
        }
    }
}
