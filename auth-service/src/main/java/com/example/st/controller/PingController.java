package com.example.st.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "OK"; // Or use service name like "AUTH-SERVICE OK"
    }
}
