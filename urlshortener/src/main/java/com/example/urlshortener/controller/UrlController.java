/*************************************************************************
    > File Name: UrlController.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Fri Dec 27 19:49:58 2024
 ************************************************************************/
package com.example.urlshortener.controller;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.model.User;
import com.example.urlshortener.service.UrlService;
import com.example.urlshortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UrlController {
    
    @Autowired
    private UrlService urlService;
    
    @Autowired
    private UserService userService;
    
    // 縮短 URL
    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody UrlRequest urlRequest) {
        String originalUrl = urlRequest.getOriginalUrl();
        String customAlias = urlRequest.getCustomAlias();
        
        User user = null;
        // 獲取當前登入用戶
        if(SecurityContextHolder.getContext().getAuthentication() != null &&
           SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
           !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<User> optionalUser = userService.findByUsername(username);
            if(optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }
        
        try {
            Url url = urlService.createShortUrl(user, originalUrl, customAlias);
            return ResponseEntity.ok(new UrlResponse(url.getShortUrl(), url.getOriginalUrl()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // 重定向到原始 URL
    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirectToOriginal(@PathVariable String shortUrl) {
        Optional<Url> optionalUrl = urlService.getOriginalUrl(shortUrl);
        if(optionalUrl.isPresent()) {
            Url url = optionalUrl.get();
            
            // 檢查是否過期
            if(url.getExpirationDate() != null && url.getExpirationDate().isBefore(java.time.LocalDateTime.now())) {
                return ResponseEntity.status(HttpStatus.GONE).body("這個短網址已經過期");
            }
            
            urlService.incrementClickCount(url);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(java.net.URI.create(url.getOriginalUrl()));
            return new ResponseEntity<>(headers, HttpStatus.FOUND); // 使用 302 重定向
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("短網址未找到");
        }
    }
    
    // 顯示用戶所有 URL
    @GetMapping("/user/urls")
    public ResponseEntity<?> getUserUrls() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> optionalUser = userService.findByUsername(username);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ResponseEntity.ok(user.getUrls());
        } else {
            return ResponseEntity.status(401).body("未經授權");
        }
    }
    
    // UrlRequest 類別
    public static class UrlRequest {
        private String originalUrl;
        private String customAlias;
        
        // Getters 和 Setters
        public String getOriginalUrl() { return originalUrl; }
        public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }
        public String getCustomAlias() { return customAlias; }
        public void setCustomAlias(String customAlias) { this.customAlias = customAlias; }
    }
    
    // UrlResponse 類別
    public static class UrlResponse {
        private String shortUrl;
        private String originalUrl;
        
        public UrlResponse(String shortUrl, String originalUrl) {
            this.shortUrl = shortUrl;
            this.originalUrl = originalUrl;
        }
        
        // Getters
        public String getShortUrl() { return shortUrl; }
        public String getOriginalUrl() { return originalUrl; }
    }
}

