package com.cwa.springboot_app.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.entity.Projet;
import java.util.List;


@Repository
public interface ProjectRepository extends JpaRepository<Projet, Long>{
	Optional<Projet> findByNom(String nom);
	List<Projet> findByClub(Club club);

}