/*************************************************************************
    > File Name: UrlController.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Fri Dec 27 19:49:58 2024
 ************************************************************************/
package com.example.urlshortener.controller;

import com.example.urlshortener.model.User;
import com.example.urlshortener.model.Url;
import com.example.urlshortener.repository.UserRepository;
import com.example.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UrlRepository urlRepository;

    // 示例：獲取用戶的所有 URL
    @GetMapping("/user/{username}/urls")
    public List<Url> getUserUrls(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUrls(); // 確保 User 類中有 getUrls() 方法
    }

    // 其他端點，如創建短網址、重定向等
}

