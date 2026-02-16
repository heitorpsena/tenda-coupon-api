package br.com.tenda.tenda_coupon_api.application;

import br.com.tenda.tenda_coupon_api.domain.exception.ResourceNotFoundException;
import br.com.tenda.tenda_coupon_api.domain.model.Coupon;
import br.com.tenda.tenda_coupon_api.domain.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCouponService {

    private final CouponRepository repository;

    public void execute(UUID id) {
        Coupon coupon = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cupom não encontrado"));

        coupon.delete();

        repository.save(coupon);
    }
}