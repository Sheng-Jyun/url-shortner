/*************************************************************************
    > File Name: /Users/yrt12378/Desktop/course/coding/url_project/urlshortener/src/main/java/com/example/urlshortener/controller/AuthenticationRequest.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Sun Dec 29 13:56:13 2024
 ************************************************************************/
package com.example.urlshortener.controller;

public class AuthenticationRequest {
    private String username;
    private String password;

    // 無參構造函數
    public AuthenticationRequest() {}

    // 參數構造函數
    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
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
}

