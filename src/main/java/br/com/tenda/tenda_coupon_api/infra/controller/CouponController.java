package br.com.tenda.tenda_coupon_api.infra.controller;

import br.com.tenda.tenda_coupon_api.application.CreateCouponService;
import br.com.tenda.tenda_coupon_api.application.DeleteCouponService;
import br.com.tenda.tenda_coupon_api.domain.model.Coupon;
import br.com.tenda.tenda_coupon_api.infra.dto.CreateCouponRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Coupons", description = "Coupon API")
@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CreateCouponService createCouponService;
    private final DeleteCouponService deleteCouponService;

    @Operation(summary = "Create a new coupon")
    @PostMapping
    public ResponseEntity<Coupon> create(@Valid @RequestBody CreateCouponRequest req) {
        return ResponseEntity.ok(createCouponService.execute(req));
    }

    @Operation(summary = "Delete a coupon")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteCouponService.execute(id);
        return ResponseEntity.noContent().build();
    }

}
