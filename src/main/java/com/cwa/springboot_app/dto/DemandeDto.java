package com.cwa.springboot_app.dto;

import com.cwa.springboot_app.entity.EnumEtat;
import com.cwa.springboot_app.entity.TypeDemande;

public class DemandeDto {
    private long idDemande;
    private String description;
    private TypeDemande typeDemande;
    private EnumEtat etat;

    public long getIdDemande() {
    return idDemande;
    }

    public void setIdDemande(long idDemande) {
        this.idDemande = idDemande;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    public EnumEtat getEtat() {
        return etat;
    }

    public void setEtat(EnumEtat etat) {
        this.etat = etat;
    }
}
