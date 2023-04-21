package com.ltp.globalsuperstore.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ltp.globalsuperstore.Constants;

public class CategoryValidation implements ConstraintValidator<Category, String> {

    String[] Categories = Constants.CATEGORIES;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        for (String valueItem : Categories) {
            if (value.equals(valueItem)) {
                return true;
            }
        }
        return false;
        
    }

}
