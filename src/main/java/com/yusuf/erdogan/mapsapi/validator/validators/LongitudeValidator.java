package com.yusuf.erdogan.mapsapi.validator.validators;

import com.yusuf.erdogan.mapsapi.validator.ValidLongitude;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LongitudeValidator implements ConstraintValidator<ValidLongitude, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && value >= -180 && value <= 180;
    }
}
