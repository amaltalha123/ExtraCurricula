package com.cwa.springboot_app.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@EqualsAndHashCode(callSuper = true) // car on hérite de User
public class Etudiant extends User{
   
    private String cne;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Role> rolesClub;

    @OneToMany(mappedBy = "prisePar", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tache> taches;

   
}
