package br.com.tenda.tenda_coupon_api.domain.model;

import br.com.tenda.tenda_coupon_api.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CouponTest {

    @Test
    void shouldCreateValidCoupon() {
        Coupon coupon = validCoupon();

        assertNotNull(coupon);
        assertEquals("ABC123", coupon.getCode());
        assertFalse(coupon.isDeleted());
    }

    @Test
    void shouldNormalizeCodeRemovingSpecialCharacters() {
        Coupon coupon = new Coupon(
                "AB-12@34",
                "teste",
                new BigDecimal("1"),
                LocalDate.now().plusDays(1),
                true
        );

        assertEquals("AB1234", coupon.getCode());
    }

    @Test
    void shouldFailWhenCodeNotSixCharacters() {
        assertThrows(BusinessException.class, () ->
                new Coupon(
                        "ABC1",
                        "teste",
                        new BigDecimal("1"),
                        LocalDate.now().plusDays(1),
                        true
                )
        );
    }

    @Test
    void shouldFailWhenDiscountLowerThanMinimum() {
        assertThrows(BusinessException.class, () ->
                new Coupon(
                        "ABC123",
                        "teste",
                        new BigDecimal("0.2"),
                        LocalDate.now().plusDays(1),
                        false
                )
        );
    }

    @Test
    void shouldFailWhenDiscountIsNull() {
        assertThrows(BusinessException.class, () ->
                new Coupon(
                        "ABC123",
                        "teste",
                        null,
                        LocalDate.now().plusDays(1),
                        true
                )
        );
    }

    @Test
    void shouldFailWhenExpirationDateIsPast() {
        assertThrows(BusinessException.class, () ->
                new Coupon(
                        "ABC123",
                        "teste",
                        new BigDecimal("1"),
                        LocalDate.now().minusDays(1),
                        false
                )
        );
    }

    @Test
    void shouldSoftDeleteCoupon() {
        Coupon coupon = validCoupon();

        coupon.delete();

        assertTrue(coupon.isDeleted());
    }

    @Test
    void shouldNotAllowDeleteTwice() {
        Coupon coupon = validCoupon();

        coupon.delete();

        assertThrows(BusinessException.class, coupon::delete);
    }

    @Test
    void shouldFailWhenCodeIsNull() {
        assertThrows(BusinessException.class, () ->
                new Coupon(
                        null,
                        "teste",
                        new BigDecimal("1"),
                        LocalDate.now().plusDays(1),
                        true
                )
        );
    }

    @Test
    void shouldFailWhenDescriptionIsNull() {
        assertThrows(BusinessException.class, () ->
                new Coupon(
                        "ABC123",
                        null,
                        new BigDecimal("1"),
                        LocalDate.now().plusDays(1),
                        true
                )
        );
    }

    @Test
    void shouldFailWhenDescriptionIsBlank() {
        assertThrows(BusinessException.class, () ->
                new Coupon(
                        "ABC123",
                        "   ",
                        new BigDecimal("1"),
                        LocalDate.now().plusDays(1),
                        true
                )
        );
    }

    @Test
    void shouldDefaultPublishedToFalseWhenNull() {
        Coupon coupon = new Coupon(
                "ABC123",
                "teste",
                new BigDecimal("1"),
                LocalDate.now().plusDays(1),
                null
        );

        assertFalse(coupon.getPublished());
    }

    @Test
    void shouldFailWhenCodeHasOnlySpecialCharacters() {
        assertThrows(BusinessException.class, () ->
                new Coupon(
                        "@@@@@@",
                        "teste",
                        new BigDecimal("1"),
                        LocalDate.now().plusDays(1),
                        true
                )
        );
    }

    private Coupon validCoupon() {
        return new Coupon(
                "ABC123",
                "teste",
                new BigDecimal("1"),
                LocalDate.now().plusDays(1),
                false
        );
    }
}
