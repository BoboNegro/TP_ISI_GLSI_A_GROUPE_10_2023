package frey.house.ega.services;

import frey.house.ega.dto.RetraitDto;
import frey.house.ega.dto.VersementDto;
import frey.house.ega.model.Compte;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VersementInterface {

    List<VersementDto> findAll();
    VersementDto findById(Long id);
    VersementDto save(VersementDto versementDto);

    List<VersementDto> findByCompteCible(String numero);

    List<VersementDto> findByClient(Long id);

}
