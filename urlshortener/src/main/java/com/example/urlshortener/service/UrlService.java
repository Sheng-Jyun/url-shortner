/*************************************************************************
    > File Name: UrlService.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Fri Dec 27 19:48:34 2024
 ************************************************************************/
package com.example.urlshortener.service;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.model.User;
import com.example.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {
    
    @Autowired
    private UrlRepository urlRepository;
    
    private final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final int DEFAULT_ALIAS_LENGTH = 6;
    
    // 生成隨機別名
    private String generateRandomAlias(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }
    
    // 創建短網址
    public Url createShortUrl(User user, String originalUrl, String customAlias) {
        String shortUrl;
        if(customAlias != null && !customAlias.isEmpty()) {
            // 檢查自定義別名是否已存在
            if(urlRepository.findByShortUrl(customAlias).isPresent()) {
                throw new RuntimeException("自定義別名已存在");
            }
            shortUrl = customAlias;
        } else {
            // 生成隨機別名，並確保唯一性
            do {
                shortUrl = generateRandomAlias(DEFAULT_ALIAS_LENGTH);
            } while(urlRepository.findByShortUrl(shortUrl).isPresent());
        }
        
        Url url = new Url(originalUrl, shortUrl, user);
        return urlRepository.save(url);
    }
    
    // 根據短網址查找原始網址
    public Optional<Url> getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }
    
    // 增加點擊次數並更新最後訪問時間
    public void incrementClickCount(Url url) {
        url.incrementClickCount();
        url.setLastAccessed(java.time.LocalDateTime.now());
        urlRepository.save(url);
    }
}

