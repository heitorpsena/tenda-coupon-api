package br.com.tenda.tenda_coupon_api.domain.model;

import br.com.tenda.tenda_coupon_api.domain.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue
    private UUID id;

    private String code;
    private String description;
    private BigDecimal discountValue;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expirationDate;
    
    private Boolean published;
    private boolean deleted;

    public Coupon(
            String code,
            String description,
            BigDecimal discountValue,
            LocalDate expirationDate,
            Boolean published
    ) {
        this.code = normalizeCode(code);
        this.description = description;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
        this.published = published != null ? published : false;
        this.deleted = false;
    }

    // remove caracteres especiais
    private String normalizeCode(String code) {
        String normalized = code.replaceAll("[^a-zA-Z0-9]", "");
        return normalized.toUpperCase();
    }

    public void delete() {
        if (this.deleted) {
            throw new BusinessException("Cupom já deletado");
        }
        this.deleted = true;
    }

}