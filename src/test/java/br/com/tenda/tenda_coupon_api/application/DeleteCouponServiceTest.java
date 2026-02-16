package br.com.tenda.tenda_coupon_api.application;

import br.com.tenda.tenda_coupon_api.domain.exception.ResourceNotFoundException;
import br.com.tenda.tenda_coupon_api.domain.model.Coupon;
import br.com.tenda.tenda_coupon_api.domain.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteCouponServiceTest {

    @Mock
    private CouponRepository repository;

    @InjectMocks
    private DeleteCouponService service;

    @Test
    void shouldDeleteCoupon() {
        UUID id = UUID.randomUUID();
        Coupon coupon = validCoupon();

        when(repository.findById(id)).thenReturn(Optional.of(coupon));

        service.execute(id);

        assertTrue(coupon.isDeleted());
    }

    @Test
    void shouldThrowWhenCouponNotFound() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.execute(id));
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
