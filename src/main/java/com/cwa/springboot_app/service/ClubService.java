package com.cwa.springboot_app.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwa.springboot_app.dto.EtudiantDto;
import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.entity.Role;
import com.cwa.springboot_app.entity.User;
import com.cwa.springboot_app.repository.ClubRepository;
import com.cwa.springboot_app.repository.ProjectRepository;
import com.cwa.springboot_app.repository.RoleRepository;

@Service
public class ClubService {
    private final ProjectRepository projectRepository;
    private final ClubRepository clubRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    public ClubService(ProjectRepository projectRepository,ClubRepository clubRepository,RoleRepository roleRepository,UserService userService){
    this.projectRepository =projectRepository;
    this.clubRepository =clubRepository;
    this.roleRepository =roleRepository;
    this.userService=userService;
   
    }

    public List<Projet> getClubProject(Club club){
        Optional<User> user=userService.getUser();
        if (user.isPresent()) {
            Optional<Club> clubopt=clubRepository.findByNom(club.getNom());
            if (clubopt.isPresent()) {
                List<Projet> projets = projectRepository.findByClub(clubopt.get());
                return projets;
            } else {
                throw new RuntimeException("Le club avec le nom " + club.getNom() + " n'a pas été trouvé.");
            }
        }else {
            throw new RuntimeException("L'utilisateur n'a pas été trouvé avec le token fourni.");
            
        }
       
    }

    public List<EtudiantDto> getClubMembreBureau(Club club){
        Optional<User> user=userService.getUser();
        if (user.isPresent()) {
                Optional<Club> clubopt = clubRepository.findByNom(club.getNom());
                if (clubopt.isPresent()) {
                List<Role> roles = roleRepository.findByClub(clubopt.get());
                return roles.stream().map(role -> {
                    EtudiantDto dto = new EtudiantDto();
                
                    dto.setCne(role.getEtudiant().getCne());
                    dto.setUsername(role.getEtudiant().getUsername());
                    dto.setFullname(role.getEtudiant().getFullname());
                    dto.setRoleBureau(role.getRoleBureau());
                    return dto;
                }).collect(Collectors.toList());
                
            } else {
                throw new RuntimeException("Le club avec le nom " + club.getNom() + " n'a pas été trouvé.");
            }
        }else {
            throw new RuntimeException("L'étudiant n'a pas été trouvé avec le token fourni.");
            
        }
  }
}
