package com.example.testspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class TestSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringSecurityApplication.class, args);
    }

}
