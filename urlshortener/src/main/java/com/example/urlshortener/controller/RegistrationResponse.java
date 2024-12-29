/*************************************************************************
    > File Name: src/main/java/com/example/urlshortener/controller/RegistrationResponse.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Sun Dec 29 14:58:25 2024
 ************************************************************************/
package com.example.urlshortener.controller;

public class RegistrationResponse {
    private String message;

    // 無參構造函數
    public RegistrationResponse() {}

    // 參數構造函數
    public RegistrationResponse(String message) {
        this.message = message;
    }

    // Getter 和 Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

