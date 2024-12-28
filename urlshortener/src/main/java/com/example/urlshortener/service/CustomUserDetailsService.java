/*************************************************************************
    > File Name: CustomUserDetailsService.java
    > Author: ma6174
    > Mail: ma6174@163.com 
    > Created Time: Fri Dec 27 19:51:48 2024
 ************************************************************************/
package com.example.urlshortener.service;

import com.example.urlshortener.model.User;
import com.example.urlshortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if(userOpt.isPresent()) {
            User user = userOpt.get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("用戶未找到");
        }
    }
}

