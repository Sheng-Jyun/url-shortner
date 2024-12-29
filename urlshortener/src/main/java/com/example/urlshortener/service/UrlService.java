/*************************************************************************
    > File Name: UrlService.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Fri Dec 27 19:48:34 2024
 ************************************************************************/
package com.example.urlshortener.service;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    // 示例方法：訪問短網址，增加點擊次數並設置最後訪問時間
    public Url accessShortUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        url.incrementClickCount();
        url.setLastAccessed(LocalDateTime.now());

        return urlRepository.save(url);
    }

    // 其他服務方法
}

