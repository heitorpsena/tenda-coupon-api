package br.com.tenda.tenda_coupon_api.application;

import br.com.tenda.tenda_coupon_api.domain.exception.BusinessException;
import br.com.tenda.tenda_coupon_api.domain.model.Coupon;
import br.com.tenda.tenda_coupon_api.domain.repository.CouponRepository;
import br.com.tenda.tenda_coupon_api.infra.dto.CreateCouponRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCouponServiceTest {

    @Mock
    private CouponRepository repository;

    @InjectMocks
    private CreateCouponService service;

    @Test
    void shouldCreateCoupon() {
        CreateCouponRequest request = new CreateCouponRequest(
                "ABC123",
                "teste",
                new BigDecimal("1"),
                LocalDate.now().plusDays(1),
                true
        );

        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        Coupon result = service.execute(request);

        assertNotNull(result);
        verify(repository).save(any());
    }

    @Test
    void shouldNotAllowDuplicateCouponCode() {
        CreateCouponRequest request = new CreateCouponRequest(
                "ABC123",
                "teste",
                new BigDecimal("1"),
                LocalDate.now().plusDays(1),
                true
        );

        when(repository.existsByCode("ABC123")).thenReturn(true);

        assertThrows(BusinessException.class,
                () -> service.execute(request));
    }
}
