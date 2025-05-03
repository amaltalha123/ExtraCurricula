package com.cwa.springboot_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cwa.springboot_app.service.ClubService;
import com.cwa.springboot_app.service.DemandeService;
import com.cwa.springboot_app.service.ProjectService;
import com.cwa.springboot_app.service.RolesService;
import com.cwa.springboot_app.dto.DemandeDto;
import com.cwa.springboot_app.dto.EtudiantDto;
import com.cwa.springboot_app.dto.ResponseMessage;
import com.cwa.springboot_app.dto.TacheDto;
import com.cwa.springboot_app.entity.Club;

import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.entity.Role;
import com.cwa.springboot_app.entity.Tache;

import org.springframework.web.bind.annotation.RequestHeader;

import java.util.*;

import org.springframework.http.ResponseEntity;



@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StudentClubsController {
    
        private RolesService rolesService;
        private ClubService clubService;
        private ProjectService projetctService;
        private DemandeService demandeService;

        @Autowired
        public StudentClubsController(ProjectService projetctService,RolesService rolesService,ClubService clubService,DemandeService demandeService) {
                this.rolesService = rolesService;
                this.clubService =clubService;
                this.projetctService= projetctService;
                this.demandeService= demandeService;
        }

        @GetMapping("/StudentRoles")
        public ResponseEntity<List<Role>> getClubsForStudent() {
                
                List<Role> roles = rolesService.getStudentRoles();
                return ResponseEntity.ok(roles);
        }

        @PostMapping("/ClubProjects")
        public ResponseEntity<List<Projet>> getClubProjects(@RequestBody Club club){
                
                List<Projet> projets = clubService.getClubProject(club);
                return ResponseEntity.ok(projets);
        }

        @PostMapping("/ProjectTasks")
        public ResponseEntity<List<TacheDto>> getClubProjectsTasks( @RequestBody Projet projet){
                List<TacheDto> taches = projetctService.getProjectTasks(projet);
                return ResponseEntity.ok(taches);
        }

        @PostMapping("/ProjectDemandes")
        public ResponseEntity<List<DemandeDto>> getProjectDemandes( @RequestBody Projet projet){
               
                List<DemandeDto> demandes = demandeService.getProjectDemandes(projet);
                return ResponseEntity.ok(demandes);
        }

        @PostMapping("/ClubMembreBureau")
        public ResponseEntity<List<EtudiantDto>> getClubMembreBureau( @RequestBody Club club){
                List<EtudiantDto> membresBureau = clubService.getClubMembreBureau(club);
                return ResponseEntity.ok(membresBureau);
        }

        @PostMapping("/PredreTache")
        public ResponseEntity<ResponseMessage> PrendreTache( @RequestBody Tache tache){
                if(projetctService.PredreTache(tache)){
                        return ResponseEntity.ok(new ResponseMessage("Vous avez pris la tache", true));
                }else{
                        return ResponseEntity.ok(new ResponseMessage("Une erreur s'est produite", false));  
                }
        }

        @PostMapping("/MarquerTacheTerminee")
        public ResponseEntity<ResponseMessage> TaskDone(@RequestHeader("Authorization") String token, @RequestBody Tache tache){
                String jwtToken = token.substring(7);
                if(projetctService.TaskDone(jwtToken, tache)){
                        return ResponseEntity.ok(new ResponseMessage("La tache est marqué comme términnée", true));
                }else{
                        return ResponseEntity.ok(new ResponseMessage("Une erreur s'est produite", false));   
                }
        }

       
}
