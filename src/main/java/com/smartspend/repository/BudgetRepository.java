package com.smartspend.repository;

import com.smartspend.model.Budget;
import com.smartspend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    List<Budget> findByUser(User user);
}
