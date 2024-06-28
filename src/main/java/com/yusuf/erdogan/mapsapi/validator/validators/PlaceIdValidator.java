package com.yusuf.erdogan.mapsapi.validator.validators;

import com.yusuf.erdogan.mapsapi.validator.ValidPlaceId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PlaceIdValidator implements ConstraintValidator<ValidPlaceId, String> {
    @Override
    public void initialize(ValidPlaceId constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.trim().isEmpty(); // Replace with your validation logic
    }
}
