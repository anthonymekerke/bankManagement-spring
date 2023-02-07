package com.example.BankManagement.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class AlternateFieldValidator implements ConstraintValidator<AlternateField, Object>{
    
    private String message;
    private String firstField;
    private String secondField;

    @Override
    public void initialize(final AlternateField constraintAnnotation) {
        this.message = constraintAnnotation.message();
        this.firstField = constraintAnnotation.firstField();
        this.secondField = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        Object firstValue = new BeanWrapperImpl(value).getPropertyValue(firstField);
        Object secondValue = new BeanWrapperImpl(value).getPropertyValue(secondField);
        boolean isValid = true;

        if(firstValue == null && secondValue == null){
            message = "Both fields '"+firstField+"' & '"+secondField+"' can't be null at same time";
            isValid = false;
        }

        if(firstValue != null && secondValue != null){
            message = "Both fields '"+firstField+"' & '"+secondField+"' can't be set at same time";
            isValid = false;
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }

        return isValid;
    }
}