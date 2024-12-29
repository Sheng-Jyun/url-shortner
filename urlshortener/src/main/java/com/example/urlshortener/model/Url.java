/*************************************************************************
    > File Name: Url.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Fri Dec 27 19:27:50 2024
 ************************************************************************/
package com.example.urlshortener.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "urls") // 明確指定表名
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 確保有 @Id 注解

    @Column(nullable = false, unique = true)
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String shortUrl;

    @Column(nullable = false)
    private int clickCount = 0;

    private LocalDateTime lastAccessed;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 無參構造函數
    public Url() {}

    // 三參構造函數
    public Url(String originalUrl, String shortUrl, User user) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.user = user;
    }

    // Getters 和 Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }

    public String getShortUrl() { return shortUrl; }
    public void setShortUrl(String shortUrl) { this.shortUrl = shortUrl; }

    public int getClickCount() { return clickCount; }
    public void setClickCount(int clickCount) { this.clickCount = clickCount; }

    public LocalDateTime getLastAccessed() { return lastAccessed; }
    public void setLastAccessed(LocalDateTime lastAccessed) { this.lastAccessed = lastAccessed; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    // 新增的方法
    public void incrementClickCount() {
        this.clickCount++;
    }
}

