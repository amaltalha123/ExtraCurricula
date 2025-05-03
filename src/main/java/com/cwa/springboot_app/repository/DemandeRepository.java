package com.cwa.springboot_app.repository;

    import java.util.List;
    
    import org.springframework.data.jpa.repository.JpaRepository;
    
    import com.cwa.springboot_app.entity.Projet;
    import com.cwa.springboot_app.entity.Demande;
    public interface DemandeRepository extends JpaRepository<Demande,Long>{
        List<Demande> findByProjet(Projet projet);
    }

