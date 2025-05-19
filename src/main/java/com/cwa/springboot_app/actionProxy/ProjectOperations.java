package com.cwa.springboot_app.actionProxy;

import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.entity.Tache;

public interface ProjectOperations {
    boolean creerProjet(Projet projet,Club club,String strategynotif);
    boolean modifierProjet(Projet projet,Club club);
    boolean CreerTache(Projet projet, Tache tache);
}
