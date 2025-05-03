package com.cwa.springboot_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Demande {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeDemande typeDemande;

    private String description;

    @Enumerated(EnumType.STRING)
    private EnumEtat etat;

    @ManyToOne
    @JsonIgnore
    private Projet projet;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    @JsonIgnore 
    private Etudiant etudiant;
}
