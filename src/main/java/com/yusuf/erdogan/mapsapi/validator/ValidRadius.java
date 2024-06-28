package com.yusuf.erdogan.mapsapi.validator;

import com.yusuf.erdogan.mapsapi.validator.validators.RadiusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RadiusValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRadius {
    String message() default "Invalid Radius";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
