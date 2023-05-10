package com.example.financeApp.service;

import com.example.financeApp.dto.CategoryDTO;
import com.example.financeApp.dto.TransactionDTO;
import com.example.financeApp.entity.Category;
import com.example.financeApp.entity.Transaction;
import com.example.financeApp.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public Category create(CategoryDTO dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .isIncome(dto.getIsIncome())
                .build();
        return categoryRepository.save(category);
    }

    public List<Category> readAll() {
        return categoryRepository.findAll();
    }
    public Category readById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Category not found - " + id));
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

}
