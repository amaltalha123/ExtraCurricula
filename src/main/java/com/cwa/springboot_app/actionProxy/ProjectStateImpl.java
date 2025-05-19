package com.cwa.springboot_app.actionProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.cwa.springboot_app.StatePattern.ProjectState;
import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.entity.Projet;

@Component
public class ProjectStateImpl implements ProjectStateOperations{

    private ApplicationContext applicationContext;

    @Autowired
       public ProjectStateImpl(ApplicationContext applicationContext) {
           this.applicationContext = applicationContext;
       }
       
    @Override
    public boolean changerEtat(Projet projet,Club club) {
        ProjectState newstate =StateFactory.getProjectNewState(projet,applicationContext);
        if (newstate != null) {
            return newstate.changerEtat(projet);
        } else {
            throw new IllegalArgumentException("L'état du projet est invalide ou non pris en charge.");
        }
        
    }

}
