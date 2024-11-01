package com.imane.controle.repositories;
import com.imane.controle.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {
    @Query("SELECT v FROM Vente v JOIN v.produits p WHERE p.id = :produitId")
    List<Vente> findByProduitId(@Param("produitId") Long produitId);
}
