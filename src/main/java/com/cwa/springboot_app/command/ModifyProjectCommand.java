package com.cwa.springboot_app.command;

import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.repository.ProjectRepository;
import java.util.Optional;

public class ModifyProjectCommand implements Command {

    private final ProjectRepository projectRepository;
    private final Projet projet;

    public ModifyProjectCommand(ProjectRepository projectRepository, Projet projet) {
        this.projectRepository = projectRepository;
        this.projet = projet;
       
    }

    @Override
    public boolean execute() {
        try {
            Optional<Projet> existingProject = projectRepository.findById(projet.getId());
            if (existingProject.isEmpty()) return false;

            Projet p = existingProject.get();
            p.setNom(projet.getNom());
            p.setDescription(projet.getDescription());
            p.setDateDebut(projet.getDateDebut());
            p.setDateFin(projet.getDateFin());
            p.setAffiche(projet.getAffiche());
            p.setDossierSponsoring(projet.getDossierSponsoring());

            return projectRepository.save(p) != null;
        } catch (Exception e) {
            System.err.println("Erreur lors de la modification du projet : " + e.getMessage());
            return false;
        }
    }
}