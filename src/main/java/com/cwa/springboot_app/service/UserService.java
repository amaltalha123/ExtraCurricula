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
    private final UsersRepository usersRepository;
    private static final Map<String, User> userCache = new HashMap<>();

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<User> getUser () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Utilisateur non authentifié");
        }

        String username = authentication.getName();
        System.out.println("Extracted username: " + username);

        // Vérifiez si l'utilisateur est déjà dans le cache
        if (userCache.containsKey(username)) {
            return Optional.of(userCache.get(username));
        }

        // Si l'utilisateur n'est pas dans le cache, interrogez la base de données
        Optional<User> user = usersRepository.findByUsername(username);
        if (user.isPresent()) {
            userCache.put(username, user.get()); 
        } else {
            System.out.println("No user found with username: " + username);
        }

        return user;
    }

   
}