package frey.house.ega.repositories;

import frey.house.ega.model.Compte;
import frey.house.ega.model.Versement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersementRepository extends JpaRepository<Versement, Long> {

    List<Versement> findByCompteCibleOrderByDateVersementDesc(Compte compte);

    @Query("select v from Versement v where v.compteCible.proprietaire.id = ?1")
    List<Versement> findByClient(Long id);
}
