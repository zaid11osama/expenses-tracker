package com.smartspend.service;

import com.smartspend.dto.AuthRequest;
import com.smartspend.dto.AuthResponse;
import com.smartspend.model.User;
import com.smartspend.repository.UserRepository;
import com.smartspend.utility.JwtUtil;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;

    public AuthResponse login(AuthRequest authRequest) {
        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        if(!passwordEncoder.matches(authRequest.getPassword(),user.getPassword())){
            throw new IllegalArgumentException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail(),user.getRole().name());
        return new AuthResponse(token);
    }

    public String register(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";


        }
    }


