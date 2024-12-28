/*************************************************************************
    > File Name: AuthController.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Fri Dec 27 19:49:29 2024
 ************************************************************************/
package com.example.urlshortener.controller;

import com.example.urlshortener.model.User;
import com.example.urlshortener.service.UserService;
import com.example.urlshortener.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    // 用戶註冊
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
            return ResponseEntity.ok("用戶註冊成功");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // 用戶登入
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("用戶名或密碼錯誤");
        }
        
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    
    // AuthenticationResponse 類別
    public static class AuthenticationResponse {
        private final String jwt;
        
        public AuthenticationResponse(String jwt) {
            this.jwt = jwt;
        }
        
        public String getJwt() {
            return jwt;
        }
    }
}

