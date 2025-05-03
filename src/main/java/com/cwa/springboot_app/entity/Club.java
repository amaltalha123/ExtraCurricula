package com.cwa.springboot_app.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Club {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Date dateCreation;
    private String description;

    @OneToMany(mappedBy = "club")
    @JsonIgnore
    private List<Role> bureau;

    @OneToMany(mappedBy = "club")
    @JsonIgnore
    private List<Projet> projet;
}
