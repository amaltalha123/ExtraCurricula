package com.cwa.springboot_app.repository;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwa.springboot_app.entity.Role;
import com.cwa.springboot_app.entity.Etudiant;
import com.cwa.springboot_app.entity.Club;
import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{
	List<Role> findByEtudiant(Etudiant etudiant);
	List<Role> findByClub(Club club);

}
