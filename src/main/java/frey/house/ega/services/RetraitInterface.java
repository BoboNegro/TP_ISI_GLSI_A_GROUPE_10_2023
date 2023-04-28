package frey.house.ega.services;

import frey.house.ega.dto.RetraitDto;
import frey.house.ega.model.Compte;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RetraitInterface {

    List<RetraitDto> findAll();
    RetraitDto findById(Long id);
    RetraitDto save(RetraitDto retraitDto);

    List<RetraitDto> findByCompteCible(String numero);

    List<RetraitDto> findByClient(Long id);
}
