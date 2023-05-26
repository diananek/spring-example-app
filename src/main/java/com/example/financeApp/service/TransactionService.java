package com.example.financeApp.service;

import com.example.financeApp.dto.TransactionDTO;
import com.example.financeApp.entity.Transaction;
import com.example.financeApp.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryService categoryService;

    public Transaction create(TransactionDTO dto) {
        Transaction transaction = Transaction.builder()
                .amount(dto.getAmount())
                .date(dto.getDate())
                .isIncome(dto.getIsIncome())
                .category(categoryService.readById(dto.getCategoryId()))
                .comment(dto.getComment())
                .build();
        return transactionRepository.save(transaction);
    }

    public List<Transaction> readAll() {
        return transactionRepository.findAll();
    }
    public List<Transaction> readByCategoryId(Long id) {
        return transactionRepository.findByCategoryId(id);
    }

    public Transaction update(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
}
