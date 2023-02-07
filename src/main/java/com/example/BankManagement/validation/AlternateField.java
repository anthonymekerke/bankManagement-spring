package com.example.BankManagement.validation;

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
@Constraint(validatedBy = AlternateFieldValidator.class)
public @interface AlternateField {

    String message() default "first & second field not valid";
    String firstField();
    String secondField();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    /////////////////////////////////////
    /// Applying a list of validation ///
    /////////////////////////////////////
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {

        AlternateField[] value();

    }

    // Example
    // @AlternateField.List({
    //     @AlternateField(firstField = "withdraw", secondField = "payment"),
    //     @AlternateField(firstField = "login", secondField = "email")
    // })
}