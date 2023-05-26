package com.example.financeApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Boolean isIncome;
    private Date date;
    private String comment;
    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName="id")
    private Category category;
}
