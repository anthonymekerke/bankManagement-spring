package bankManagement.accountService.controller.hal.modelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import bankManagement.accountService.controller.hal.HalAccountController;
import bankManagement.accountService.controller.hal.HalSavingAccountController;
import bankManagement.accountService.domain.SavingAccountResponse;

@Component
public class SavingAccountModelAssembler extends ModelAssembler<SavingAccountResponse>{

    @Override
    public EntityModel<SavingAccountResponse> toModel(SavingAccountResponse dto) {
        return EntityModel.of(dto,
            linkTo(methodOn(HalSavingAccountController.class).getCurrentAccountsId(dto.getId(), authentication)).withSelfRel(),
            linkTo(methodOn(HalAccountController.class).getAccountsId(dto.getId(), authentication)).withRel("account")
        );
    }
    
}