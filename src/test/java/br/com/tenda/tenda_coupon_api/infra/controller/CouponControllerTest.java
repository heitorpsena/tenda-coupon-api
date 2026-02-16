package br.com.tenda.tenda_coupon_api.infra.controller;

import br.com.tenda.tenda_coupon_api.application.CreateCouponService;
import br.com.tenda.tenda_coupon_api.application.DeleteCouponService;
import br.com.tenda.tenda_coupon_api.domain.exception.BusinessException;
import br.com.tenda.tenda_coupon_api.domain.model.Coupon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CouponController.class)
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateCouponService createCouponService;

    @MockBean
    private DeleteCouponService deleteCouponService;

    @Test
    void shouldCreateCouponEndpoint() throws Exception {
        var request = """
                {
                  "code": "ABC123",
                  "description": "teste",
                  "discountValue": 1,
                  "expirationDate": "%s",
                  "published": true
                }
                """.formatted(LocalDate.now().plusDays(1));

        when(createCouponService.execute(any())).thenReturn(mockCoupon());

        mockMvc.perform(post("/coupons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteCouponEndpoint() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(deleteCouponService).execute(id);

        mockMvc.perform(delete("/coupons/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnBadRequestWhenBusinessExceptionIsThrown() throws Exception {

        var request = """
        {
          "code": "ABC123",
          "description": "teste",
          "discountValue": 1,
          "expirationDate": "%s",
          "published": true
        }
        """.formatted(LocalDate.now().plusDays(1));

        when(createCouponService.execute(any()))
                .thenThrow(new BusinessException("Code deve ter 6 caracteres"));

        mockMvc.perform(post("/coupons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNotFoundWhenRuntimeExceptionIsThrown() throws Exception {

        UUID id = UUID.randomUUID();

        doThrow(new RuntimeException("erro"))
                .when(deleteCouponService)
                .execute(id);

        mockMvc.perform(delete("/coupons/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenDeletingAlreadyDeletedCoupon() throws Exception {
        UUID id = UUID.randomUUID();

        doThrow(new BusinessException("Cupom já deletado"))
                .when(deleteCouponService)
                .execute(id);

        mockMvc.perform(delete("/coupons/" + id))
                .andExpect(status().isBadRequest());
    }

    private Coupon mockCoupon() {
        return new Coupon(
                "ABC123",
                "teste",
                new BigDecimal("1"),
                LocalDate.now().plusDays(1),
                true
        );
    }

}
