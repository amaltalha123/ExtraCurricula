package com.cwa.springboot_app.StatePattern;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cwa.springboot_app.dto.TacheDto;
import com.cwa.springboot_app.entity.EnumEtatProjet;
import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.repository.ProjectRepository;
import com.cwa.springboot_app.service.ProjectService;


 @Component
public class ChangerVersEtatTerminee implements ProjectState{
    private final ProjectRepository projectRepository;
    private ProjectService projectService;
    
   @Autowired
    public ChangerVersEtatTerminee(ProjectRepository projectRepository, ProjectService projectService) {
        this.projectRepository = projectRepository;
        this.projectService = projectService;
    }
    @Override
    public boolean changerEtat(Projet projet) {
        List<TacheDto> projectTasks = projectService.getProjectTasks(projet);
        for (TacheDto tache : projectTasks) {
            if (!tache.isEstTerminee()) {
                System.out.println("Une tache n'est pas encore términé, changement d'état échoué.");
                return false;
            }
        }

        Optional<Projet> existingProject = projectRepository.findById(projet.getId());
        if (existingProject.isEmpty()) {
            System.out.println("Le projet n'existe pas.");
            return false;
        }

        Projet p = existingProject.get();
        p.setEtatProjet(EnumEtatProjet.TERMINEE);
        return projectRepository.save(p) != null;
        
    }

}
