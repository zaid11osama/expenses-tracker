package com.smartspend.repository;

import com.smartspend.model.Income;
import com.smartspend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUser(User user);
}
