package com.example.financeApp.controller;

import com.example.financeApp.dto.TransactionDTO;
import com.example.financeApp.service.TransactionService;
import com.example.financeApp.entity.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
@Tag(name = "transactions", description = "Transaction controller (income and expenses)")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @Operation(summary = "Creating a transaction")
    public ResponseEntity<Transaction> create(@RequestBody TransactionDTO dto) {
        return new ResponseEntity<>(transactionService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Returns all transactions")
    public ResponseEntity<List<Transaction>> readAll() {
        return new ResponseEntity<>(transactionService.readAll(), HttpStatus.OK);
    }
    @Operation(summary = "Returns transaction by category id")
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Transaction>> readByCategoryId(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.readByCategoryId(id), HttpStatus.OK);
    }

    @Operation(summary = "Updating transaction by id")
    @PutMapping
    public ResponseEntity<Transaction> update(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.update(transaction), HttpStatus.OK);
    }

    @Operation(summary = "Deleting transaction by id")
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        transactionService.delete(id);
        return HttpStatus.OK;
    }
}
