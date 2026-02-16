package br.com.tenda.tenda_coupon_api.infra.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateCouponRequest(

        @Schema(example = "ABC123")
        String code,

        @Schema(example = "10% discount")
        String description,

        @Schema(example = "1.5")
        BigDecimal discountValue,

        @Schema(example = "2027-12-31")
        LocalDate expirationDate,

        boolean published
) {}
