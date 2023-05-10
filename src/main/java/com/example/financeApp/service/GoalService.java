package com.example.financeApp.service;

import com.example.financeApp.dto.GoalDTO;
import com.example.financeApp.dto.TransactionDTO;
import com.example.financeApp.entity.Category;
import com.example.financeApp.entity.Goal;
import com.example.financeApp.entity.Transaction;
import com.example.financeApp.repository.GoalRepository;
import com.example.financeApp.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@AllArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;

    public Goal create(GoalDTO dto) {
        Goal goal = Goal.builder()
                .name(dto.getName())
                .amount(dto.getAmount())
                .endDate(dto.getEndDate())
                .startDate(dto.getStartDate())
                .build();
        return goalRepository.save(goal);
    }

    public List<Goal> readAll() {
        return goalRepository.findAll();
    }

    public Goal update(Goal goal) {
        return goalRepository.save(goal);
    }
    public void delete(Long id) {
        goalRepository.deleteById(id);
    }

    public Goal readById(Long id) {
        return goalRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Goal not found - " + id));
    }
    public Double calculateRegularDeposit(Long id) {
        Goal goal = goalRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Goal not found - " + id));
        Integer days = Math.toIntExact((goal.getEndDate().getTime() - goal.getStartDate().getTime()) / 86400000);
        return goal.getAmount()/ days;
    }
}
