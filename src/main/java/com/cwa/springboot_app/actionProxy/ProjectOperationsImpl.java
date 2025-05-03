package com.cwa.springboot_app.actionProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cwa.springboot_app.command.CommandInvoker;
import com.cwa.springboot_app.command.Command;
import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.entity.Projet;

import com.cwa.springboot_app.actionProxy.CommandFactory;



@Component
public class ProjectOperationsImpl implements ProjectOperations{

    @Autowired
    private CommandFactory factory;
    
    private final CommandInvoker invoker = new CommandInvoker();

    @Override
    public boolean creerProjet(Projet projet, Club club) {
        Command cmd = factory.createCreateProjectCommand(club, projet);
        return invoker.executerCommande(cmd);
    }
    @Override
    public boolean modifierProjet(Projet projet,Club club) {
        Command cmd = factory.createModifyProjectCommand(projet);
        return invoker.executerCommande(cmd);
    }
     
}
