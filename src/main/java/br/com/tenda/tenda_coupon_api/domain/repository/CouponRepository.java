package br.com.tenda.tenda_coupon_api.domain.repository;

import br.com.tenda.tenda_coupon_api.domain.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {

    boolean existsByCode(String code);
}
