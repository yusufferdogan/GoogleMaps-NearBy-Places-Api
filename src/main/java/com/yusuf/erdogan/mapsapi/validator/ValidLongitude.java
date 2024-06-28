package com.yusuf.erdogan.mapsapi.validator;

import com.yusuf.erdogan.mapsapi.validator.validators.LongitudeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LongitudeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLongitude {
    String message() default "Invalid Longitude";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
