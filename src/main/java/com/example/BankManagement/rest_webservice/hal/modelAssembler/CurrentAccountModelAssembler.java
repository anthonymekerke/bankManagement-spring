package com.example.BankManagement.rest_webservice.hal.modelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import com.example.BankManagement.business.dto.CurrentAccountBasicDTO;
import com.example.BankManagement.rest_webservice.hal.HalAccountController;
import com.example.BankManagement.rest_webservice.hal.HalCurrentAccountController;

@Component
public class CurrentAccountModelAssembler extends ModelAssembler<CurrentAccountBasicDTO>{

    @Override
    public EntityModel<CurrentAccountBasicDTO> toModel(CurrentAccountBasicDTO dto) {
        return EntityModel.of(dto,
            linkTo(methodOn(HalCurrentAccountController.class).getCurrentAccountsId(dto.getId(), authentication)).withSelfRel(),
            linkTo(methodOn(HalAccountController.class).getAccountsId(dto.getId(), authentication)).withRel("account")
        );
    }
    
}
