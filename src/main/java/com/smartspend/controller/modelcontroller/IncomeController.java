package com.smartspend.controller.modelcontroller;

import com.smartspend.model.Income;
import com.smartspend.service.modelservice.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping("/user/{userId}")
    public Income addIncome(@PathVariable int userId, @RequestBody Income income) {
        return incomeService.addIncome(userId, income);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getAllIncomes(@PathVariable int userId) {

        try {
            List<Income> incomes= incomeService.getAllIncome(userId);
            return ResponseEntity.status(HttpStatus.OK).body(incomes);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.OK).body(Collections.emptyList());
        }
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<String> deleteIncome(@PathVariable int incomeId) {

        String response = incomeService.deleteIncome(incomeId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
