package com.cwa.springboot_app.actionProxy;

import com.cwa.springboot_app.StatePattern.ChangerVersEtatEnCours;
import com.cwa.springboot_app.StatePattern.ChangerVersEtatTerminee;
import com.cwa.springboot_app.StatePattern.ProjectState;
import com.cwa.springboot_app.entity.EnumEtatProjet;
import com.cwa.springboot_app.entity.Projet;
import org.springframework.context.ApplicationContext;

public class StateFactory {

    public static ProjectState getProjectNewState(Projet projet,ApplicationContext applicationContext) {
        if (projet.getEtatProjet().equals(EnumEtatProjet.EN_ATTENTE)) {
            return applicationContext.getBean(ChangerVersEtatEnCours.class);
        } else if (projet.getEtatProjet().equals(EnumEtatProjet.EN_COURS)) {
            return applicationContext.getBean(ChangerVersEtatTerminee.class);
        }
        return null;
    }
}
