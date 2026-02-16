package br.com.tenda.tenda_coupon_api.application;

import br.com.tenda.tenda_coupon_api.domain.exception.BusinessException;
import br.com.tenda.tenda_coupon_api.domain.model.Coupon;
import br.com.tenda.tenda_coupon_api.domain.repository.CouponRepository;
import br.com.tenda.tenda_coupon_api.infra.dto.CreateCouponRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCouponService {

    private final CouponRepository repository;

    public Coupon execute(CreateCouponRequest req) {
        Coupon coupon = new Coupon(
                req.code(),
                req.description(),
                req.discountValue(),
                req.expirationDate(),
                req.published()
        );

        // valida unicidade do código
        if (repository.existsByCode(coupon.getCode())) {
            throw new BusinessException("Cupom com este código já existe");
        }

        return repository.save(coupon);
    }
}
