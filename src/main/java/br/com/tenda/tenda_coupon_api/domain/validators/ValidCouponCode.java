package br.com.tenda.tenda_coupon_api.domain.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CouponCodeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCouponCode {

    String message() default "Code deve ter 6 caracteres alfanuméricos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}