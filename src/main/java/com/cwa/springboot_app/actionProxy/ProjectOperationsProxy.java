package com.cwa.springboot_app.actionProxy;

import org.springframework.stereotype.Component;

import com.cwa.springboot_app.Exeptions.InsufficientRoleException;
import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.entity.Tache;
import com.cwa.springboot_app.service.RolesService;
import com.cwa.springboot_app.service.UserService;


@Component
public class ProjectOperationsProxy implements ProjectOperations,ProjectStateOperations{
       
    private UserService userService;
    private RolesService rolesService;
    private ProjectOperationsImpl projectOperationsImpl;
    private ProjectStateImpl projectStateImpl;
    
    
    public ProjectOperationsProxy(ProjectOperationsImpl projectOperationsImpl,UserService userService,RolesService rolesService,ProjectStateImpl projectStateImpl) {
        this.userService = userService;
        this.rolesService=rolesService;
        this.projectOperationsImpl=projectOperationsImpl;
        this.projectStateImpl=projectStateImpl;
    }

    public boolean hasGlobalRole(String globalRole) {
        return userService.getUser ()
            .map(user -> user.getRole().name().equals(globalRole))
            .orElse(false);
    }

    public boolean hasClubRole(Club club, String clubRole) {
        String studentClubRole = rolesService.getStudentClubRole(club);
        return studentClubRole.equals(clubRole);
    }

    

  
    @Override
    public boolean creerProjet(Projet projet, Club club,String strategynotif) {
        
        if(hasGlobalRole("ETUDIANT")){
            if(hasClubRole(club, "President")){
                return projectOperationsImpl.creerProjet(projet, club,strategynotif);
            }else{
                throw new InsufficientRoleException("L'utilisateur doit être président du club pour créer un projet.");
            }
        }else{
            throw new InsufficientRoleException("L'utilisateur doit être un étudiant pour créer un projet.");
        }
    }

    @Override
    public boolean modifierProjet(Projet projet,Club club) {
        if (hasGlobalRole("ETUDIANT")) {
            if (hasClubRole(club, "President")) {
                return projectOperationsImpl.modifierProjet(projet,club);
            } else {
                throw new InsufficientRoleException("L'utilisateur doit être président du club pour modifier un projet.");
            }
        } else {
            throw new InsufficientRoleException("L'utilisateur doit être un étudiant pour modifier un projet.");
        }
    }

    @Override
    public boolean changerEtat(Projet projet,Club club) {
        if(hasGlobalRole("ETUDIANT")){
            if(hasClubRole(club, "President")){
                return projectStateImpl.changerEtat(projet, club);
            }else{
                throw new InsufficientRoleException("L'utilisateur doit être président du club pour créer un projet.");
            }
        }else{
            throw new InsufficientRoleException("L'utilisateur doit être un étudiant pour créer un projet.");
        }
    }

    @Override
    public boolean CreerTache(Projet projet, Tache tache) {
        if(hasGlobalRole("ETUDIANT")){
            if(hasClubRole(projet.getClub(), "President")){
                return projectOperationsImpl.CreerTache(projet,tache);
            }else{
                throw new InsufficientRoleException("L'utilisateur doit être président du club pour créer un projet.");
            }
        }else{
            throw new InsufficientRoleException("L'utilisateur doit être un étudiant pour créer un projet.");
        }
    }

    


}
