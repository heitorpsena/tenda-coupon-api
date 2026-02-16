package br.com.tenda.tenda_coupon_api.domain.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DiscountValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDiscount {

    String message() default "Desconto mínimo é 0.5";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
