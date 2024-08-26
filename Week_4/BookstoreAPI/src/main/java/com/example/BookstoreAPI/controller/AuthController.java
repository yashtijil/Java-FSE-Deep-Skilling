package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String authenticateUser(@RequestBody AuthRequest authRequest) {

        return jwtUtil.generateToken(authRequest.getUsername());
    }
}

class AuthRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
}