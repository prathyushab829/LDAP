package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LDAPAuthController {

    @GetMapping("/home")
    public String homeMethod(){
        return "Hello";
    }
}
