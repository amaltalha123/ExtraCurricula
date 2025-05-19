package com.cwa.springboot_app.command;

import java.util.Optional;

import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.entity.EnumEtatProjet;
import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.entity.Tache;
import com.cwa.springboot_app.repository.ClubRepository;
import com.cwa.springboot_app.repository.ProjectRepository;
import com.cwa.springboot_app.repository.TaskRepository;

public class CreateTaskCommand implements Command{
    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;
    private Tache tache;
    private Projet projet;
    
    public CreateTaskCommand(TaskRepository taskRepository,ProjectRepository projectRepository,Tache tache,Projet projet){
	   this.taskRepository=taskRepository;
       this.projectRepository=projectRepository;
	   this.tache=tache;
	   this.projet=projet;
   }
    @Override
    public boolean execute() {
        try {
            Optional<Projet> projetOpt = projectRepository.findById(projet.getId());
            if (projetOpt.isEmpty()) {
                return false;
            }

            Projet projet = projetOpt.get();
            Tache t = new Tache();
            t.setProjet(projet);
            t.setEstTerminee(false);
            t.setDescription(tache.getDescription());
           
            return taskRepository.save(t) != null;

        } catch (Exception e) {
            System.err.println("Erreur lors de la création de la nouvelle tache : " + e.getMessage());
            return false;
        }
    }

}
