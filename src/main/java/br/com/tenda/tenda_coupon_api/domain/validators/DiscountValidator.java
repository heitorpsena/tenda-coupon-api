package br.com.tenda.tenda_coupon_api.domain.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class DiscountValidator implements ConstraintValidator<ValidDiscount, BigDecimal> {

    private static final BigDecimal MIN_DISCOUNT = new BigDecimal("0.5");

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {

        if (value == null) return true;

        return value.compareTo(MIN_DISCOUNT) >= 0;
    }
}