package com.smartspend.controller.modelcontroller;


import com.smartspend.model.Expense;
import com.smartspend.service.modelservice.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {


    @Autowired
    private ExpenseService expenseService;


    @PostMapping("/user/{userId}")
    public Expense addExpense(@PathVariable int userId, @RequestBody Expense expense) {

        return expenseService.createExpense(userId, expense);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getAllExpenses(@PathVariable int userId) {
        try{
            List<Expense> expenses = expenseService.getAllExpenses(userId);

        return ResponseEntity.status(HttpStatus.OK).body(expenses);

        }catch (RuntimeException e){

            return ResponseEntity.status(HttpStatus.OK).body(Collections.emptyList());

        }
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable int expenseId) {
        String response =expenseService.deleteExpense(expenseId);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


}
