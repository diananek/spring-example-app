package com.example.financeApp.repository;

import com.example.financeApp.entity.Goal;
import com.example.financeApp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

}
