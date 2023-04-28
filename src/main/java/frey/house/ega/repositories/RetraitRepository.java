package frey.house.ega.repositories;

import frey.house.ega.model.Compte;
import frey.house.ega.model.Retrait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RetraitRepository extends JpaRepository<Retrait, Long> {
    List<Retrait> findAllByCompteCibleOrderByDateRetraitDesc(Compte compte);

    @Query("select r from Retrait r where r.compteCible.proprietaire.id = ?1")
    List<Retrait> findByClient(Long id);


}
