package com.cwa.springboot_app.command;



import org.springframework.stereotype.Component;


import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.entity.EnumEtatProjet;

import com.cwa.springboot_app.repository.ClubRepository;
import com.cwa.springboot_app.repository.ProjectRepository;
import java.util.Optional;

public class CreateProjectCommand implements Command{

    private ProjectRepository projectRepository;
	private ClubRepository clubRepository;
    private Club club;
    private Projet projet;
    
    public CreateProjectCommand(ClubRepository clubRepository,ProjectRepository projectRepository,Club club,Projet projet){
	   this.clubRepository=clubRepository;
       this.projectRepository=projectRepository;
	   this.club=club;
	   this.projet=projet;
   }
    @Override
    public boolean execute() {
        try {
            Optional<Club> clubOpt = clubRepository.findByNom(club.getNom());
            if (clubOpt.isEmpty()) {
                return false;
            }

            Club club = clubOpt.get();
            Projet p = new Projet();
            p.setClub(club);
            p.setDateDebut(projet.getDateDebut());
            p.setDateFin(projet.getDateFin());
            p.setDescription(projet.getDescription());
            p.setAffiche(projet.getAffiche());
            p.setDossierSponsoring(projet.getDossierSponsoring());
            p.setNom(projet.getNom());
            p.setEtatProjet(EnumEtatProjet.EN_ATTENTE);
            return projectRepository.save(p) != null;

        } catch (Exception e) {
            System.err.println("Erreur lors de la création du projet : " + e.getMessage());
            return false;
        }
    }

}
