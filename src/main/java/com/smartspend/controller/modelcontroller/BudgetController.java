package com.smartspend.controller.modelcontroller;

import com.smartspend.model.Budget;
import com.smartspend.service.modelservice.BudgetService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;


    //will happen after authentication
//    @PostMapping
//    public Budget createBudget(@RequestBody Budget budget, Authentication authentication) {
//        String username = authentication.getName();
//        return budgetService.createBudgetForUsername(username, budget);
//    }



    @PostMapping("/user/{userId}")
    public Budget createBudget(@PathVariable int userId, @RequestBody Budget budget) {

        return budgetService.createBudget(userId, budget);


    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserBudgets(@PathVariable int userId) {
        try{

        List <Budget> budgets = budgetService.getAllBudgets(userId);
        return ResponseEntity.status(HttpStatus.OK).body(budgets);
        }catch (RuntimeException e){
            return ResponseEntity.ok(Collections.emptyList());
        }

    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity <String> deleteBudget(@PathVariable int budgetId) {
       String response = budgetService.deleteBudget(budgetId);
       return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
