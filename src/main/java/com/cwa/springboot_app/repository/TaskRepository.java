package com.cwa.springboot_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwa.springboot_app.entity.Projet;
import com.cwa.springboot_app.entity.Tache;

public interface TaskRepository extends JpaRepository<Tache,Integer>{
	List<Tache> findByProjet(Projet projet);
	Tache findByIdTache(int idTache);
}
