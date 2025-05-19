package com.cwa.springboot_app.StatePattern;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cwa.springboot_app.dto.DemandeDto;
import com.cwa.springboot_app.entity.EnumEtat;
import com.cwa.springboot_app.entity.EnumEtatProjet;
import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.repository.ProjectRepository;
import com.cwa.springboot_app.service.DemandeService;

@Component
public class ChangerVersEtatEnCours implements ProjectState{

    private final ProjectRepository projectRepository;
    private DemandeService demandeService;
    
   @Autowired
    public ChangerVersEtatEnCours(ProjectRepository projectRepository, DemandeService demandeService) {
        this.projectRepository = projectRepository;
        this.demandeService = demandeService;
    }

    @Override
 
public boolean changerEtat(Projet projet) {
   
        List<DemandeDto> projectDemands = demandeService.getProjectDemandes(projet);
        for (DemandeDto dm : projectDemands) {
            if (dm.getEtat().equals(EnumEtat.EN_ATTENTE)) {
                System.out.println("Une demande est en attente, changement d'état échoué.");
                return false;
            }
        }

        Optional<Projet> existingProject = projectRepository.findById(projet.getId());
        if (existingProject.isEmpty()) {
            System.out.println("Le projet n'existe pas.");
            return false;
        }

        Projet p = existingProject.get();
        p.setEtatProjet(EnumEtatProjet.EN_COURS);
        return projectRepository.save(p) != null;
    
}


}
