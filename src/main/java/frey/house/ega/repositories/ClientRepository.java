package frey.house.ega.repositories;

import frey.house.ega.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findClientByEtatOrderByIdDesc(boolean etat);
}
