package bankManagement.accountService.rest_webservice.hal.modelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import bankManagement.accountService.business.dto.SavingAccountBasicDTO;
import bankManagement.accountService.rest_webservice.hal.HalAccountController;
import bankManagement.accountService.rest_webservice.hal.HalSavingAccountController;

@Component
public class SavingAccountModelAssembler extends ModelAssembler<SavingAccountBasicDTO>{

    @Override
    public EntityModel<SavingAccountBasicDTO> toModel(SavingAccountBasicDTO dto) {
        return EntityModel.of(dto,
            linkTo(methodOn(HalSavingAccountController.class).getCurrentAccountsId(dto.getId(), authentication)).withSelfRel(),
            linkTo(methodOn(HalAccountController.class).getAccountsId(dto.getId(), authentication)).withRel("account")
        );
    }
    
}