package com.ltp.globalsuperstore.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryValidation.class)
public @interface Category {
    String message() default "Please choose a category";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
