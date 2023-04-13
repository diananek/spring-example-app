package com.example.financeApp.controller;

import com.example.financeApp.service.TransactionService;
import com.example.financeApp.model.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/transactions")
@AllArgsConstructor
@Tag(name = "transactions", description = "Transaction controller (income and expenses)")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    @Operation(summary = "Get all transactions")
    public void getTransactions() {
    }

    @PostMapping
    @Operation(summary = "Add transaction")
    public void addTransaction(@RequestBody Transaction transaction) {
    }

    @GetMapping("/{idTransaction}")
    @Operation(summary = "Get transaction by id")
    public Transaction getTransactionById(@PathVariable Long idTransaction) {
        return null;
    }
}
