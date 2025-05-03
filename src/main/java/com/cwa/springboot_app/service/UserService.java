package com.cwa.springboot_app.service;

import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cwa.springboot_app.configuration.JwtUtils;
import com.cwa.springboot_app.entity.User;
import com.cwa.springboot_app.repository.UsersRepository;

@Service
public class UserService {
      private final UsersRepository usersRepository ;


    @Autowired
    public UserService(UsersRepository usersRepository,JwtUtils jwtUtils) {
        this.usersRepository = usersRepository;
        
    }  

    public Optional<User> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Utilisateur non authentifié");
        }

        String username = authentication.getName();
        System.out.println("Extracted username: " + username);

        Optional<User> user = usersRepository.findByUsername(username);
        if (!user.isPresent()) {
            System.out.println("No user found with username: " + username);
        }
        return user;
    }

}
