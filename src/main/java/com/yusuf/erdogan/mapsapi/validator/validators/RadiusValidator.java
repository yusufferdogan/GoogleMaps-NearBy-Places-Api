package com.yusuf.erdogan.mapsapi.validator.validators;

import com.yusuf.erdogan.mapsapi.validator.ValidRadius;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RadiusValidator implements ConstraintValidator<ValidRadius, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value > 0; // Define your own range if needed
    }
}
