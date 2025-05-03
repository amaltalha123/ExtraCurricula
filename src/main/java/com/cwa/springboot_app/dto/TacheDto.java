package com.cwa.springboot_app.dto;

public class TacheDto {
        private int idTache;
        private String description;
        private boolean estTerminee;
        private String priseParNom;
    
    
        // Constructeur
        public TacheDto() {
        }
    
        public TacheDto(int idTache, String description, boolean estTerminee, String priseParNom) {
            this.idTache = idTache;
            this.description = description;
            this.estTerminee = estTerminee;
            this.priseParNom = priseParNom;
        }
    
        // Getters et Setters
        public int getIdTache() {
            return idTache;
        }
    
        public void setIdTache(int idTache) {
            this.idTache = idTache;
        }
    
        public String getDescription() {
            return description;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }
    
        public boolean isEstTerminee() {
            return estTerminee;
        }
    
        public void setEstTerminee(boolean estTerminee) {
            this.estTerminee = estTerminee;
        }
    
        public String getPriseParNom() {
            return priseParNom;
        }
    
        public void setPriseParNom(String priseParNom) {
            this.priseParNom = priseParNom;
        }
    
}
