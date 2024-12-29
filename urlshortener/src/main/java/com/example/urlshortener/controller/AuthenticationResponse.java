/*************************************************************************
    > File Name: /Users/yrt12378/Desktop/course/coding/url_project/urlshortener/src/main/java/com/example/urlshortener/controller/AuthenticationResponse.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Sun Dec 29 13:56:31 2024
 ************************************************************************/
package com.example.urlshortener.controller;

public class AuthenticationResponse {
    private final String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

