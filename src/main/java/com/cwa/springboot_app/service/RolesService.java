package com.cwa.springboot_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwa.springboot_app.repository.RoleRepository;
import com.cwa.springboot_app.repository.StudentRepository;

import java.util.*;
import com.cwa.springboot_app.entity.Role;
import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.entity.Etudiant;

@Service
public class RolesService {
    final private RoleRepository roleRepository;
    final StudentRepository studentRepository;
    final StudentService studentService;

    @Autowired
    public RolesService(StudentRepository studentRepository, RoleRepository roleRepository,StudentService studentService) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.studentService=studentService;

    }  


    public List<Role> getStudentRoles(){
        Optional<Etudiant> etudiant=studentService.getStudent();
        Etudiant student=etudiant.get();
        List<Role> studentRoles=roleRepository.findByEtudiant(student);
        return studentRoles;
    }

    public String getStudentClubRole(Club club){
       List<Role> roles=getStudentRoles();
       for(Role role:roles){
         if((role.getClub().getId()).equals(club.getId())){
            return role.getRoleBureau().name();
         }
       }
        return "";
    }


}
