package com.example.BankManagement.rest_webservice.hal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankManagement.business.dto.SavingAccountBasicDTO;
import com.example.BankManagement.business.service.IAccountService;
import com.example.BankManagement.rest_webservice.hal.modelAssembler.SavingAccountModelAssembler;
import com.example.BankManagement.util.AppConstants;

@RestController
@RequestMapping("/HAL")
public class HalSavingAccountController {
    @Autowired
    @Qualifier(AppConstants.SAVING_ACCOUNT_TYPE)
    private IAccountService accountService;

    @Autowired
    private SavingAccountModelAssembler modelAssembler;

    @GetMapping("/saving-accounts/{id}")
    public EntityModel<SavingAccountBasicDTO> getCurrentAccountsId(@PathVariable(value="id")int id,Authentication authentication){
        SavingAccountBasicDTO dto = (SavingAccountBasicDTO)accountService.readByIdAndClientLogin(id, authentication.getName());
        modelAssembler.setAuthentication(authentication);
        return modelAssembler.toModel(dto); 
    }
}