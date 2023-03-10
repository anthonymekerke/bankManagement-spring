package bankManagement.accountService.rest_webservice.hal.modelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import bankManagement.accountService.business.dto.CurrentAccountBasicDTO;
import bankManagement.accountService.rest_webservice.hal.HalAccountController;
import bankManagement.accountService.rest_webservice.hal.HalCurrentAccountController;

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
