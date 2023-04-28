package frey.house.ega.repositories;

import frey.house.ega.model.Compte;
import frey.house.ega.model.Versement;
import frey.house.ega.model.Virement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VirementRepository extends JpaRepository<Virement, Long> {

    List<Virement> findByCompteDebiteOrderByDateExecutionDesc(Compte compte);

    List<Virement> findByCompteCrediteOrderByDateExecutionDesc(Compte compte);

    @Query("select v from Virement v where v.compteCredite.proprietaire.id = ?1")
    List<Virement> findByClientCrediteur(Long id);

    @Query("select v from Virement v where v.compteDebite.proprietaire.id = ?1")
    List<Virement> findByClientDebiteur(Long id);
}
