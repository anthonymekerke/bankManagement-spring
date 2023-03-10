package bankManagement.accountService.rest_webservice.hal.modelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import bankManagement.accountService.business.dto.AccountBasicDTO;
import bankManagement.accountService.rest_webservice.hal.HalAccountController;
import bankManagement.accountService.rest_webservice.hal.HalCurrentAccountController;
import bankManagement.accountService.rest_webservice.hal.HalSavingAccountController;
import bankManagement.accountService.util.AppConstants;

@Component
public class AccountModelAssembler extends ModelAssembler<AccountBasicDTO>{

    @Override
    public EntityModel<AccountBasicDTO> toModel(AccountBasicDTO dto) {
        WebMvcLinkBuilder accountDetailsLink = null;

        if(dto.getAccountType() == AppConstants.CURRENT_ACCOUNT_TYPE){
            accountDetailsLink = linkTo(methodOn(HalCurrentAccountController.class).getCurrentAccountsId(dto.getId(), authentication));
        }
        if(dto.getAccountType() == AppConstants.SAVING_ACCOUNT_TYPE){
            accountDetailsLink = linkTo(methodOn(HalSavingAccountController.class).getCurrentAccountsId(dto.getId(), authentication));
        }

        if(accountDetailsLink == null){return null;}

        return EntityModel.of(dto,
            linkTo(methodOn(HalAccountController.class).getAccountsId(dto.getId(), authentication)).withSelfRel(),
            linkTo(methodOn(HalAccountController.class).getAccountsIdTransactions(dto.getId(), authentication)).withRel("transactions"),
            linkTo(methodOn(HalAccountController.class).getAccounts(authentication)).withRel("accounts"),
            accountDetailsLink.withRel("details")
        );
    }   
}
