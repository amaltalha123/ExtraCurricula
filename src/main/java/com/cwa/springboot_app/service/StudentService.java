package com.cwa.springboot_app.service;


import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cwa.springboot_app.configuration.JwtUtils;
import com.cwa.springboot_app.entity.Etudiant;
import com.cwa.springboot_app.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private static final Map<String, Etudiant> studentCache = new HashMap<>();

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Etudiant> getStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Utilisateur non authentifié");
        }

        String username = authentication.getName();
        System.out.println("Extracted username: " + username);

        // Vérifiez si l'étudiant est déjà dans le cache
        if (studentCache.containsKey(username)) {
            return Optional.of(studentCache.get(username));
        }

        // Si l'étudiant n'est pas dans le cache, interrogez la base de données
        Optional<Etudiant> etudiantOpt = studentRepository.findByUsername(username);
        if (etudiantOpt.isPresent()) {
            studentCache.put(username, etudiantOpt.get()); // Mettez l'étudiant dans le cache
        } else {
            System.out.println("No student found with username: " + username);
        }

        return etudiantOpt;
    }
}
