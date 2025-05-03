package com.cwa.springboot_app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwa.springboot_app.repository.ProjectRepository;
import com.cwa.springboot_app.dto.DemandeDto;
import com.cwa.springboot_app.dto.TacheDto;
import com.cwa.springboot_app.entity.Demande;
import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.entity.Tache;
import com.cwa.springboot_app.entity.User;
import com.cwa.springboot_app.repository.DemandeRepository;
@Service
public class DemandeService {
    private final DemandeRepository demandeRepository;
    private final ProjectRepository projectRepository;
    private final UserService userService;

    @Autowired
    public DemandeService(ProjectRepository projectRepository,DemandeRepository demandeRepository,UserService userService){
        this.projectRepository =projectRepository;
        this.demandeRepository= demandeRepository;
        this.userService=userService;
       
    }

    public List<DemandeDto> getProjectDemandes(Projet projet) {
    Optional<User> user=userService.getUser();
    if (user.isPresent()) {  
        Optional<Projet> projetopt = projectRepository.findByNom(projet.getNom());
        if (projetopt.isPresent()) {
            List<Demande> demandes = demandeRepository.findByProjet(projetopt.get());
            return demandes.stream().map(demande -> {
                DemandeDto dto = new DemandeDto();
                dto.setIdDemande(demande.getId());
                dto.setDescription(demande.getDescription());
                dto.setEtat(demande.getEtat());
                dto.setTypeDemande(demande.getTypeDemande());
                return dto;
            }).collect(Collectors.toList());
        } else {
            throw new RuntimeException("Le club avec le nom " + projet.getNom() + " n'a pas été trouvé.");
        }
   }else{
        throw new RuntimeException("Le user n'est pas trouvé ");
   }
}
}
