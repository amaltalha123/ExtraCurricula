package com.cwa.springboot_app.dto;

import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.entity.Projet;


    public class CreateProjectRequest {
        private Club club;
        private Projet projet;
        private String strategy;
    
        // Getters et Setters
        public Club getClub() {
            return club;
        }
    
        public void setClub(Club club) {
            this.club = club;
        }
    
        public Projet getProjet() {
            return projet;
        }
    
        public void setProjet(Projet projet) {
            this.projet = projet;
        }
    
        public String getStrategy() { 
            return strategy;
        }
    
        public void setStrategy(String strategy) {
            this.strategy = strategy;
        }
    }