package com.programmers.vouchermanagement.voucher.presentation;

import com.programmers.vouchermanagement.voucher.application.VoucherService;
import com.programmers.vouchermanagement.voucher.dto.request.VoucherCreationRequest;
import com.programmers.vouchermanagement.voucher.dto.request.VoucherUpdateRequest;
import com.programmers.vouchermanagement.voucher.dto.response.VoucherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v3/vouchers")
@RequiredArgsConstructor
public class RestApiVoucherController {

    private final VoucherService voucherService;

    @PostMapping
    public ResponseEntity<VoucherResponse> createVoucher(@RequestBody VoucherCreationRequest request) {
        log.info("{}", request);
        VoucherResponse response = voucherService.createVoucher(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<VoucherResponse>> getVouchers() {
        List<VoucherResponse> responses = voucherService.getVouchers();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{voucherId}")
    public ResponseEntity<VoucherResponse> getVoucher(@PathVariable UUID voucherId) {
        log.info("voucherId={}", voucherId);
        VoucherResponse response = voucherService.getVoucher(voucherId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{voucherId}")
    public ResponseEntity<Void> updateVoucher(@PathVariable UUID voucherId, @RequestBody VoucherUpdateRequest request) {
        log.info("voucherId={}, {}", voucherId, request);
        voucherService.updateVoucher(voucherId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{voucherId}")
    public ResponseEntity<Void> deleteVoucher(@PathVariable UUID voucherId) {
        log.info("voucherId={}", voucherId);
        voucherService.deleteVoucher(voucherId);
        return ResponseEntity.noContent().build();
    }
}
