package com.example.financeApp.controller;

import com.example.financeApp.service.BalanceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/balance")
@AllArgsConstructor
@Tag(name = "balance", description = "Finance balance controller")
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping("/{balanceId}")
    public Double getBalance(@PathVariable Long balanceId) {
        return balanceService.getBalance();
    }

    @PostMapping("add/{balanceId}/{amount}")
    public Double addMoney(@PathVariable Long balanceId, @PathVariable Double amount) {
        return balanceService.addMoney(balanceId, amount);
    }

    @PostMapping("remove/{balanceId}/{amount}")
    public Double removeMoney(@PathVariable Long balanceId, @PathVariable Double amount) {
        return balanceService.removeMoney(balanceId, amount);

    }
}
