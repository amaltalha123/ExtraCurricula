package com.cwa.springboot_app.dto;

import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.entity.Tache;

public class CreateTaskRequest {
 
        private Projet projet;
        private Tache tache;
    
        // Getters et Setters
        public Tache getTache() {
            return tache;
        }
    
        public void setClub(Tache tache) {
            this.tache=tache;
        }
    
        public Projet getProjet() {
            return projet;
        }
    
        public void setProjet(Projet projet) {
            this.projet = projet;
        }
    
        
    }