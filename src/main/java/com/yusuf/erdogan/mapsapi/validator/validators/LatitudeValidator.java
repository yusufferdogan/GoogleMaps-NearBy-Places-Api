package com.yusuf.erdogan.mapsapi.validator.validators;

import com.yusuf.erdogan.mapsapi.validator.ValidLatitude;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LatitudeValidator implements ConstraintValidator<ValidLatitude, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && value >= -90 && value <= 90;
    }
}
