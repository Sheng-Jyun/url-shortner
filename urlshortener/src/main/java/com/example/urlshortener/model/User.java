/*************************************************************************
    > File Name: User.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Fri Dec 27 19:27:26 2024
 ************************************************************************/
package com.example.urlshortener.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "users") // 避免與 PostgreSQL 的保留字 'user' 冲突
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 確保有 @Id 注解

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Url> urls = new ArrayList<>();

    // 無參構造函數
    public User() {}

    // 三參構造函數
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters 和 Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Url> getUrls() { return urls; }
    public void setUrls(List<Url> urls) { this.urls = urls; }

    // 添加和移除 URL 方法
    public void addUrl(Url url) {
        urls.add(url);
        url.setUser(this);
    }

    public void removeUrl(Url url) {
        urls.remove(url);
        url.setUser(null);
    }
}

