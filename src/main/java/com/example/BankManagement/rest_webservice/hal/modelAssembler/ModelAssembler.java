package com.example.BankManagement.rest_webservice.hal.modelAssembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.security.core.Authentication;

public abstract class ModelAssembler<BasicDTO> implements RepresentationModelAssembler<BasicDTO, EntityModel<BasicDTO>>{
    
    protected Authentication authentication;

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
