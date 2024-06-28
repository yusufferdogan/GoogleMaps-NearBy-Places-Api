package com.yusuf.erdogan.mapsapi.validator;

import com.yusuf.erdogan.mapsapi.validator.validators.LatitudeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LatitudeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLatitude {
    String message() default "Invalid Latitude";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
