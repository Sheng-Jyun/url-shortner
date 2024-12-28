/*************************************************************************
    > File Name: UserService.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Fri Dec 27 19:48:08 2024
 ************************************************************************/
package com.example.urlshortener.service;

import com.example.urlshortener.model.User;
import com.example.urlshortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // 用戶註冊
    public User registerUser(String username, String email, String password) {
        // 檢查用戶名和郵箱是否已存在
        if(userRepository.existsByUsername(username)) {
            throw new RuntimeException("用戶名已存在");
        }
        if(userRepository.existsByEmail(email)) {
            throw new RuntimeException("郵箱已存在");
        }
        
        // 哈希密碼
        String encodedPassword = passwordEncoder.encode(password);
        
        User user = new User(username, email, encodedPassword);
        return userRepository.save(user);
    }
    
    // 根據用戶名查找用戶
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

