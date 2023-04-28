package frey.house.ega.services;

import frey.house.ega.dto.RetraitDto;
import frey.house.ega.dto.VirementDto;
import frey.house.ega.model.Compte;
import frey.house.ega.model.Virement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VirementInterface {

    List<VirementDto> findAll();
    VirementDto findById(Long id);
    VirementDto save(VirementDto virementDto);

    List<VirementDto> findByCompteDebite(String numero);

    List<VirementDto> findByCompteCredite(String numero);

    List<VirementDto> findByClientCrediteur(Long id);

    List<VirementDto> findByClientDebiteur(Long id);

}
