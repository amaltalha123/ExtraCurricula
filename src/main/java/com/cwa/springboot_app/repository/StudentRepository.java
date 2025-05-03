package com.cwa.springboot_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwa.springboot_app.entity.Etudiant;

public interface StudentRepository extends JpaRepository<Etudiant, Long>{
        Optional<Etudiant> findByUsername(String username);

}
