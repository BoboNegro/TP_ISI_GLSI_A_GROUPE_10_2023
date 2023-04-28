package frey.house.ega.repositories;

import frey.house.ega.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

        Compte findByNumero(String numero);
        List<Compte> findCompteByEtatOrderByIdDesc(boolean etat);


}
