package com.smartspend.service.modelservice;

import com.smartspend.model.Income;
import com.smartspend.model.User;
import com.smartspend.repository.IncomeRepository;
import com.smartspend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private UserRepository userRepository;


    public Income addIncome(int userId , Income income){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );
            income.setUser(user);
           return incomeRepository.save(income);


    }

    public List<Income> getAllIncome(int userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        return incomeRepository.findByUser(user);

    }


    public String deleteIncome(long incomeId){

        Income income = incomeRepository.findById(incomeId).orElse(null);

        if(income != null){
            incomeRepository.delete(income);
            return "Income with id " +incomeId+ " successfully deleted";
        }
        return "Income with id " +incomeId+ " not found";
    }

}
