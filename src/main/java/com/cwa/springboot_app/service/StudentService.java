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
      private final StudentRepository studentRepository ;

      private final JwtUtils jwtUtils;

    @Autowired
    public StudentService(StudentRepository studentRepository,JwtUtils jwtUtils) {
        this.studentRepository = studentRepository;
        
        this.jwtUtils =jwtUtils;
    }  

    public Optional<Etudiant> getStudent() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Utilisateur non authentifié");
        }

        String username = authentication.getName(); 
        System.out.println("Extracted username: " + username);

        Optional<Etudiant> etudiantOpt = studentRepository.findByUsername(username);
        
        if (!etudiantOpt.isPresent()) {
            System.out.println("No student found with username: " + username);
        }

        return etudiantOpt;
    }



}
