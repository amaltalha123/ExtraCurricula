package com.cwa.springboot_app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwa.springboot_app.dto.TacheDto;
import com.cwa.springboot_app.entity.Etudiant;
import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.entity.Tache;
import com.cwa.springboot_app.entity.User;
import com.cwa.springboot_app.repository.ProjectRepository;
import com.cwa.springboot_app.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final StudentService studentService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,TaskRepository taskRepository,UserService userService,StudentService studentService){
        this.projectRepository =projectRepository;
        this.taskRepository =taskRepository;
        this.userService =userService;
        this.studentService =studentService;
    }


    public List<TacheDto> getProjectTasks(Projet projet) {

        Optional<User> user=userService.getUser();
        if (user.isPresent()) {
            Optional<Projet> projetopt = projectRepository.findByNom(projet.getNom());
            if (projetopt.isPresent()) {
                List<Tache> taches = taskRepository.findByProjet(projetopt.get());
                return taches.stream().map(tache -> {
                    TacheDto dto = new TacheDto();
                    dto.setIdTache(tache.getIdTache());
                    dto.setDescription(tache.getDescription());
                    dto.setEstTerminee(tache.isEstTerminee());
                    dto.setPriseParNom(tache.getPrisePar() != null ? tache.getPrisePar().getUsername() : null);
                    return dto;
                }).collect(Collectors.toList());
            } else {
                throw new RuntimeException("Le club avec le nom " + projet.getNom() + " n'a pas été trouvé.");
            }
            }else {
                throw new RuntimeException("L'utilisateur n'a pas été trouvé avec le token fourni.");
                
            }
    }

    public boolean PredreTache(Tache tache){
        Optional<Etudiant> etudiantopt=studentService.getStudent();
        //the verification if that student is a member in the club must be added
        
        if (etudiantopt.isPresent()) {
            Etudiant etudiant = etudiantopt.get(); 
            Optional<Tache> tacheOpt = taskRepository.findById(tache.getIdTache()); 
            
            if (tacheOpt.isPresent()) {
                Tache existingTache = tacheOpt.get();
                existingTache.setPrisePar(etudiant); 
                taskRepository.save(existingTache);
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean TaskDone(String token,Tache tache){
        Optional<Etudiant> etudiantopt=studentService.getStudent();
        if (etudiantopt.isPresent()) {
            Optional<Tache> tacheOpt = taskRepository.findById(tache.getIdTache()); 
            
            if (tacheOpt.isPresent()) {
                Tache existingTache = tacheOpt.get();
                existingTache.setEstTerminee(true);
                taskRepository.save(existingTache);
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }


}
