package com.cwa.springboot_app.actionProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.entity.Tache;
import com.cwa.springboot_app.entity.Club;

import com.cwa.springboot_app.command.CreateProjectCommand;
import com.cwa.springboot_app.command.ModifyProjectCommand;
import com.cwa.springboot_app.command.CreateTaskCommand;
import com.cwa.springboot_app.repository.ClubRepository;
import com.cwa.springboot_app.repository.ProjectRepository;
import com.cwa.springboot_app.repository.TaskRepository;

@Component
public class CommandFactory {
    
   @Autowired
   private ProjectRepository projectRepository;

   @Autowired
   private ClubRepository clubRepository;

   @Autowired
   private TaskRepository taskRepository;

     public CreateProjectCommand createCreateProjectCommand(Club club, Projet projet) {
        return new CreateProjectCommand(clubRepository,projectRepository,club, projet);
     }

     public ModifyProjectCommand createModifyProjectCommand(Projet projet) {
      return new ModifyProjectCommand(projectRepository, projet);
     }

     public CreateTaskCommand CreateTaskCommand(Projet projet,Tache tache) {
      return new CreateTaskCommand(taskRepository,projectRepository,tache,projet);
     }
}
