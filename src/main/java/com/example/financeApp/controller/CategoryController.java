package com.example.financeApp.controller;

import com.example.financeApp.dto.CategoryDTO;
import com.example.financeApp.entity.Category;
import com.example.financeApp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Tag(name = "categories", description = "Transaction categories")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping
    @Operation(summary = "Creating a category")
    public ResponseEntity<Category> create (@RequestBody CategoryDTO dto) {
        return new ResponseEntity<>(categoryService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Returns all categories")
    public ResponseEntity<List<Category>> readAll() {
        return new ResponseEntity<>(categoryService.readAll(), HttpStatus.OK);
    }

    @Operation(summary = "Updating category by id")
    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.update(category), HttpStatus.OK);
    }

    @Operation(summary = "Deleting category by id")
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        categoryService.delete(id);
        return HttpStatus.OK;
    }
}
