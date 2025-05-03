package com.cwa.springboot_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@Entity
public class Tache {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTache;

    private String description;
    private boolean estTerminee;

    @ManyToOne
    @JsonIgnore
    private Etudiant prisePar;

    @ManyToOne
   
    private Projet projet;
}
