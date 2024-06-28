package com.yusuf.erdogan.mapsapi.validator;

import com.yusuf.erdogan.mapsapi.validator.validators.PlaceIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PlaceIdValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPlaceId {

    String message() default "Invalid Place ID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
