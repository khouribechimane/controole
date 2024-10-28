package com.imane.controle.repositories;
import com.imane.controle.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenteRepository extends CrudRepository<Vente, Long> {
}
