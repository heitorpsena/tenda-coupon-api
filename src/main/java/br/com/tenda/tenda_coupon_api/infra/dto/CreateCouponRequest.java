package br.com.tenda.tenda_coupon_api.infra.dto;

import br.com.tenda.tenda_coupon_api.domain.validators.ValidCouponCode;
import br.com.tenda.tenda_coupon_api.domain.validators.ValidDiscount;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateCouponRequest(

        @Schema(example = "ABC123")
        @NotBlank(message = "Code é obrigatório")
        @ValidCouponCode
        String code,

        @Schema(example = "10% discount")
        @NotNull
        @NotBlank(message = "Description é obrigatório")
        String description,

        @Schema(example = "1.5")
        @NotNull
        @ValidDiscount
        BigDecimal discountValue,

        @Schema(example = "2027-12-31")
        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull
        @FutureOrPresent(message = "Data não pode ser no passado")
        LocalDate expirationDate,

        boolean published
) {}
