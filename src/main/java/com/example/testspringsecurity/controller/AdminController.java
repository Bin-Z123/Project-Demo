package com.example.testspringsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @RequestMapping("/home")
    public String admin(){
        return "Hello Admin!";
    }
}
