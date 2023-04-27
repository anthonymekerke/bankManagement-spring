package bankManagement.accountService.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartWithPrefixesValidator implements ConstraintValidator<StartWithPrefixes, String> {

    String[] prefixes = { "VIR", "PRLV", "PAIEMENT", "VRST", "RETRAIT", "F", "RETRO", "IMPAYE" };

    @Override
    public void initialize(final StartWithPrefixes constraintAnnotation) {}

    @Override
    public boolean isValid(final String wording, final ConstraintValidatorContext context) {
        String prefixToCheck = wording.split(" ", 2)[0];
        for(String prefix: prefixes){
            if(prefix.equals(prefixToCheck)){return true;}
        }
        return false;
    }
}