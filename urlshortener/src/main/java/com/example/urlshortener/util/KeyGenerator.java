/*************************************************************************
    > File Name: src/main/java/com/example/urlshortener/util/KeyGenerator.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Sat Dec 28 02:54:00 2024
 ************************************************************************/
package com.example.urlshortener.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("HS512 Key: " + base64Key);
    }
}

