package bankManagement.accountService.business.dto.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 * Here is a Class Level Validation.
 * It implement a custom validator input field.
 */
@Documented
@Retention(RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = AlternateNullFieldsValidator.class)
public @interface AlternateNullFields {

    String message() default "first & second field not valid";
    String first();
    String second();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    /////////////////////////////////////
    /// Applying a list of validation ///
    /////////////////////////////////////
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {

        AlternateNullFields[] value();

    }

    // Example
    // @AlternateField.List({
    //     @AlternateField(firstField = "withdraw", secondField = "payment"),
    //     @AlternateField(firstField = "login", secondField = "email")
    // })
}