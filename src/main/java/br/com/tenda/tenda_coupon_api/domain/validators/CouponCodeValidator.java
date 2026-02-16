package br.com.tenda.tenda_coupon_api.domain.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CouponCodeValidator implements ConstraintValidator<ValidCouponCode, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) return true;

        String normalized = value.replaceAll("[^a-zA-Z0-9]", "");

        return normalized.length() == 6;
    }
}