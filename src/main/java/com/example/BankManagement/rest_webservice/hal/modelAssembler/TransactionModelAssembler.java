package com.example.BankManagement.rest_webservice.hal.modelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.rest_webservice.hal.HalAccountController;

@Component
public class TransactionModelAssembler extends ModelAssembler<TransactionBasicDTO>{

    private int accountId;

    @Override
    public EntityModel<TransactionBasicDTO> toModel(TransactionBasicDTO dto) {
        return EntityModel.of(dto,
            linkTo(methodOn(HalAccountController.class).getTransactionId(accountId, dto.getId(), authentication)).withSelfRel(),
            linkTo(methodOn(HalAccountController.class).getAccountsIdTransactions(accountId, authentication)).withRel("transactions"),
            linkTo(methodOn(HalAccountController.class).getAccountsId(accountId, authentication)).withRel("account")
        );
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
