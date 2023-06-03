package com.example.financeApp.controller;

import com.example.financeApp.dto.GoalDTO;
import com.example.financeApp.entity.Goal;
import com.example.financeApp.service.GoalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
@AllArgsConstructor
@Tag(name = "goals", description = "Finance goals controller")
public class GoalController {

    private final GoalService goalService;
    @GetMapping
    public ResponseEntity<List<Goal>> readAll() {
        return new ResponseEntity<>(goalService.readAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Goal> create(@RequestBody GoalDTO dto) {
        return new ResponseEntity<>(goalService.create(dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goal> readById(@PathVariable Long id) {
        return new ResponseEntity<>(goalService.readById(id), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Goal> update(@RequestBody Goal goal) {
        return new ResponseEntity<>(goalService.update(goal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        goalService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("calculate/{id}")
    public ResponseEntity<Double> calculateEverydayDeposit(@PathVariable Long id) {
        return new ResponseEntity<>(goalService.calculateEverydayDeposit(id), HttpStatus.OK);
    }
    @Operation(summary = "Calculating weekly deposit by goal id")
    @GetMapping("weekly_calculate/{id}")
    public ResponseEntity<Double> calculateWeeklyDeposit(@PathVariable Long id) {
        return new ResponseEntity<>(goalService.calculateWeeklyDeposit(id), HttpStatus.OK);
    }
    @Operation(summary = "Calculating monthly deposit by goal id")
    @GetMapping("monthly_calculate/{id}")
    public ResponseEntity<Double> calculateMonthlyDeposit(@PathVariable Long id) {
        return new ResponseEntity<>(goalService.calculateMonthlyDeposit(id), HttpStatus.OK);
    }
}
