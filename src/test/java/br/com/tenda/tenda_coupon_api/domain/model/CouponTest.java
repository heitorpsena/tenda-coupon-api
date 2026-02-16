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
    void shouldConvertCodeToUpperCase() {
        Coupon coupon = new Coupon(
                "abc123",
                "teste",
                new BigDecimal("1"),
                LocalDate.now().plusDays(1),
                true
        );

        assertEquals("ABC123", coupon.getCode());
    }

    @Test
    void shouldKeepPublishedTrueWhenProvided() {
        Coupon coupon = new Coupon(
                "ABC123",
                "teste",
                new BigDecimal("1"),
                LocalDate.now().plusDays(1),
                true
        );

        assertTrue(coupon.getPublished());
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
