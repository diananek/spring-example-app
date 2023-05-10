package com.example.financeApp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GoalDTO {
    private String name;
    private Double amount;
    private Date startDate;
    private Date endDate;
}
