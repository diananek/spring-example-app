package com.example.financeApp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDTO {
    private String name;
    private Double amount;
    private Boolean isIncome;
    private Date date;
    private Long categoryId;
    private String comment;
}
