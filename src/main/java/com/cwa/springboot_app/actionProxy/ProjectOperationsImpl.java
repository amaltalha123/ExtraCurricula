package com.cwa.springboot_app.actionProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cwa.springboot_app.command.CommandInvoker;
import com.cwa.springboot_app.command.Command;
import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.entity.Projet;

import com.cwa.springboot_app.ObserverStrategy.NotificationService;
import com.cwa.springboot_app.actionProxy.CommandFactory;



@Component
public class ProjectOperationsImpl implements ProjectOperations{

    private CommandFactory factory;
    private NotificationService notificationService;

    public ProjectOperationsImpl(CommandFactory factory, NotificationService notificationService) {
        this.factory = factory;
        this.notificationService = notificationService;
    }
    
    private final CommandInvoker invoker = new CommandInvoker();

    @Override
    public boolean creerProjet(Projet projet, Club club,String strategynotif) {
        Command cmd = factory.createCreateProjectCommand(club, projet);
        String msg="nouveau projet" + projet.getNom() +"est ajouté au club" + club.getNom();
        return invoker.executerCommande(cmd) && notificationService.notifierMembresDuBureau(club,msg,strategynotif);
    }
    @Override
    public boolean modifierProjet(Projet projet,Club club) {
        Command cmd = factory.createModifyProjectCommand(projet);
        return invoker.executerCommande(cmd) ;
    }
     
}
