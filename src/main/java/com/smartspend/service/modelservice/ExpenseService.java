package com.smartspend.service.modelservice;

import com.smartspend.model.Budget;
import com.smartspend.model.Expense;
import com.smartspend.model.User;
import com.smartspend.repository.BudgetRepository;
import com.smartspend.repository.ExpenseRepository;
import com.smartspend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {


    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BudgetRepository budgetRepository;


    public Expense createExpense(int userId,Expense expense) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User with id " + userId + " not found")
        );
        expense.setUser(user);
        return expenseRepository.save(expense);
    }


    public List<Expense> getAllExpenses(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User with id " + userId + " not found")
        );
        return expenseRepository.findByUser(user);

    }

    public String deleteExpense(int expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElse(null);

        if (expense != null) {
        expenseRepository.deleteById(expenseId);
        return "Expense deleted with id " + expenseId;

        }
        return "Expense with id " + expenseId + " not found";


    }

}
