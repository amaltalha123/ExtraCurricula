package com.cwa.springboot_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwa.springboot_app.entity.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long>{
  Optional<Club> findByNom(String nom);
  
}
