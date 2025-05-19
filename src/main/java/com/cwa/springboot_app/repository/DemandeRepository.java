package com.cwa.springboot_app.repository;

import java.util.List;
    
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.entity.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande,Long>{
        List<Demande> findByProjet(Projet projet);
}

