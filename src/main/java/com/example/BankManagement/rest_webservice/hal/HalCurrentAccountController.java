package com.example.BankManagement.rest_webservice.hal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankManagement.business.dto.CurrentAccountBasicDTO;
import com.example.BankManagement.business.service.IAccountService;
import com.example.BankManagement.rest_webservice.hal.modelAssembler.CurrentAccountModelAssembler;
import com.example.BankManagement.util.AppConstants;

@RestController
@RequestMapping("/HAL")
public class HalCurrentAccountController {

    private IAccountService accountService;
    private CurrentAccountModelAssembler modelAssembler;

    public HalCurrentAccountController(
        @Qualifier(AppConstants.CURRENT_ACCOUNT_TYPE) IAccountService accountService,
        CurrentAccountModelAssembler modelAssembler)
    {
        this.accountService = accountService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping("/current-accounts/{id}")
    public EntityModel<CurrentAccountBasicDTO> getCurrentAccountsId(@PathVariable(value="id")int id,Authentication authentication){
        CurrentAccountBasicDTO dto = (CurrentAccountBasicDTO)accountService.readByIdAndClientLogin(id, authentication.getName());
        modelAssembler.setAuthentication(authentication);
        return modelAssembler.toModel(dto); 
    }
}