package com.example.testspringsecurity.controller;

import com.example.testspringsecurity.dto.request.LoginRequest;
import com.example.testspringsecurity.dto.request.RegisterRequest;
import com.example.testspringsecurity.dto.response.ApiResponse;
import com.example.testspringsecurity.entity.User;
import com.example.testspringsecurity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {
    final AuthenticationManager authenticationManager;
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username is exists!");
        }
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());
        newUser.setFullname(request.getFullname());
        newUser.setRole(request.getRole());
        userRepository.save(newUser);
        return ResponseEntity.ok("Register success!");
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse<User>> login(@RequestBody LoginRequest request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            ApiResponse<User> apiResponse = new ApiResponse<>();
            apiResponse.setResult( userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found")));
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<User> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Invalid username or password" +e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
        }

    }
}
