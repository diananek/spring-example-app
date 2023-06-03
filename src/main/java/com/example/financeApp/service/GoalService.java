package com.example.financeApp.service;

import com.example.financeApp.dto.GoalDTO;
import com.example.financeApp.entity.Goal;
import com.example.financeApp.repository.GoalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Double calculateEverydayDeposit(Long id) {
        Goal goal = goalRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Goal not found - " + id));
        Double days = Math.floor((goal.getEndDate().getTime() - goal.getStartDate().getTime()) / (864*Math.pow(10,5)));
        return Math.ceil(goal.getAmount()/ days);
    }

    public Double calculateWeeklyDeposit(Long id) {
        Goal goal = goalRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Goal not found - " + id));
        Double weeks = Math.floor((goal.getEndDate().getTime() - goal.getStartDate().getTime()) / (6048*Math.pow(10,5)));
        return Math.ceil(goal.getAmount()/ weeks);
    }
    public Double calculateMonthlyDeposit(Long id) {
        Goal goal = goalRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Goal not found - " + id));
        Double months = Math.floor((goal.getEndDate().getTime() - goal.getStartDate().getTime()) / (2628*Math.pow(10,6)));
        return Math.ceil(goal.getAmount()/ months);
    }
}
