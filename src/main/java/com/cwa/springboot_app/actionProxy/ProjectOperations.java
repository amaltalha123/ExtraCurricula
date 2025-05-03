package com.cwa.springboot_app.actionProxy;

import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.entity.Projet;

public interface ProjectOperations {
    boolean creerProjet(Projet projet,Club club);
    boolean modifierProjet(Projet projet,Club club);
}
