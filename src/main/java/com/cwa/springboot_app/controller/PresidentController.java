package com.cwa.springboot_app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwa.springboot_app.actionProxy.ProjectOperationsProxy;
import com.cwa.springboot_app.dto.CreateProjectRequest;




@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PresidentController {
    private final ProjectOperationsProxy proxy;

    public PresidentController(ProjectOperationsProxy proxy){
        this.proxy=proxy;
    }

    @PostMapping("/CreateProject")
    public ResponseEntity<?> CreateProject(@RequestBody CreateProjectRequest createProjectRequest){
           Map<String,Object> result = new HashMap<>();
        
           boolean success=proxy.creerProjet(createProjectRequest.getProjet(),createProjectRequest.getClub());
           if(success){
                result.put("status", "success");
                result.put("message", "le Projet ets ajouté avec succées");
           }else{
                result.put("status", "error");
                result.put("message", "Une erreur est survenue lors de l'ajout du projet");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
           }
           return ResponseEntity.ok(result);
    }

    @PostMapping("/ModifyProject")
    public ResponseEntity<?> modifyProject(@RequestBody CreateProjectRequest request) {
        Map<String, Object> result = new HashMap<>();
        boolean success = proxy.modifierProjet(request.getProjet(),request.getClub());
        if (success) {
            result.put("status", "success");
            result.put("message", "Le projet a été modifié avec succès");
            return ResponseEntity.ok(result);
        } else {
            result.put("status", "error");
            result.put("message", "Une erreur est survenue lors de la modification");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

}
