package com.smartspend.service.modelservice;

import com.smartspend.model.Budget;
import com.smartspend.model.User;
import com.smartspend.repository.BudgetRepository;
import com.smartspend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    UserRepository userRepository;


    public Budget createBudget(int userId,Budget budget) {

        User user = userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("user with id :" + userId+ " not found"));

        budget.setUser(user);
        return budgetRepository.save(budget);

    }

    public List<Budget> getAllBudgets(int userId) {

        User user = userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("user with id :" + userId+ " not found"));

        return  budgetRepository.findByUser(user);

    }

    public String deleteBudget(int budgetId) {

        Budget budget = budgetRepository.findById(budgetId).orElse(null);
        if (budget != null) {
            budgetRepository.delete(budget);
            return "Budget deleted successfully";
        }
        return "Budget not found";
    }
}
