/*************************************************************************
    > File Name: JwtUtil.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Fri Dec 27 19:50:57 2024
 ************************************************************************/
package com.example.urlshortener.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.Date;
import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "jxDlQoS+EwLnD83v6OWKjraJWcs7g36cm7jqW/6gR8A="; // 至少 256 位的密鑰

    private final long EXPIRATION_TIME = 86400000; // 1 天（毫秒）

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // 生成 JWT 令牌
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // 從令牌中提取用戶名
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 驗證令牌
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // 檢查令牌是否過期
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}

