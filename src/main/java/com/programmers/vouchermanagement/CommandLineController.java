package com.programmers.vouchermanagement;

import com.programmers.vouchermanagement.view.Command;
import com.programmers.vouchermanagement.view.Console;
import com.programmers.vouchermanagement.voucher.domain.DiscountType;
import com.programmers.vouchermanagement.voucher.application.VoucherDto;
import com.programmers.vouchermanagement.voucher.presentation.VoucherController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.util.List;

@Profile("!test")
@Slf4j
@Controller
@RequiredArgsConstructor
public class CommandLineController implements CommandLineRunner {

    private final VoucherController voucherController;

    @Override
    public void run(String... args) {
        boolean running = true;

        while (running) {
            try {
                running = isRunning();
            } catch (RuntimeException e) {
                Console.outputErrorMessage(e.getMessage());
                log.error(e.getMessage());
            }

        }
    }

    private boolean isRunning() {
        Command command = Command.from(Console.selectCommand());
        switch (command) {
            case CREATE -> {
                DiscountType discountType = DiscountType.from(Console.selectDiscountType());
                int discountAmount = Console.inputDiscountAmount();
                VoucherDto request = new VoucherDto(discountType, discountAmount);
                voucherController.createVoucher(request);
            }
            case LIST-> {
                List<VoucherDto> vouchers = voucherController.getVouchers();
                Console.outputVouchers(vouchers);
            }
            case EXIT -> {
                return false;
            }
        }
        return true;
    }
}
