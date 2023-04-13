package com.example.financeApp.controller;

import com.example.financeApp.service.GoalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/goal")
@AllArgsConstructor
@Tag(name = "goals", description = "Finance goals controller")
public class GoalController {

    private final GoalService goalService;

    @GetMapping("/{goalId}")
    public Double getBalance(@PathVariable Long goalId) {
        return goalService.getBalance();
    }

    @PostMapping("add/{goalId}/{amount}")
    public Double addMoney(@PathVariable Long goalId, @PathVariable Double amount) {
        return goalService.addMoney(goalId, amount);

    }

    @PostMapping("remove/{goalId}/{amount}")
    public Double removeMoney(@PathVariable Long goalId, @PathVariable Double amount) {
        return goalService.removeMoney(goalId, amount);

    }

    @GetMapping("calculate/{goalId}")
    public Double calculateRegularDeposit(@PathVariable Long goalId) {
        return goalService.calculateRegularDeposit(goalId);
    }
}
