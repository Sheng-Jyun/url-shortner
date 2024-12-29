/*************************************************************************
    > File Name: src/main/java/com/example/urlshortener/controller/RegistrationRequest.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Sun Dec 29 14:52:41 2024
 ************************************************************************/
package com.example.urlshortener.controller;

public class RegistrationRequest {
    private String username;
    private String password;
    private String email;

    // 無參構造函數
    public RegistrationRequest() {}

    // 參數構造函數
    public RegistrationRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters 和 Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    public String getEmail() {
        return email;
    }
   
    public void setEmail(String email) {
        this.email = email;
    }
}

