package com.smartspend.repository;

import com.smartspend.model.Expense;
import com.smartspend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findByUser(User user);
}
