package br.com.tenda.tenda_coupon_api.domain.model;

import br.com.tenda.tenda_coupon_api.domain.exception.BusinessException;
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
        validateCode(this.code);
        validateDescription(description);
        validateDiscount(discountValue);
        validateExpiration(expirationDate);

        this.description = description;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
        this.published = published != null ? published : false;
        this.deleted = false;
    }

    // remove caracteres especiais
    private String normalizeCode(String code) {
        if (code == null) throw new BusinessException("Code obrigatório");

        String normalized = code.replaceAll("[^a-zA-Z0-9]", "");

        return normalized.toUpperCase();
    }

    // valida tamanho 6
    private void validateCode(String code) {
        if (code.length() != 6) {
            throw new BusinessException("Code deve ter 6 caracteres");
        }
    }

    // valida desconto mínimo
    private void validateDiscount(BigDecimal value) {
        if (value == null || value.compareTo(new BigDecimal("0.5")) < 0) {
            throw new BusinessException("Desconto mínimo é 0.5");
        }
    }

    // valida data
    private void validateExpiration(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new BusinessException("Data não pode ser no passado");
        }
    }

    //valida desc
    private void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new BusinessException("Description obrigatória");
        }
    }

    public void delete() {
        if (this.deleted) {
            throw new BusinessException("Cupom já deletado");
        }
        this.deleted = true;
    }

}