package frey.house.ega.services;

import frey.house.ega.dto.CompteDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CompteInterface {

    List<CompteDto> findAll();

    CompteDto findById(Long id);
    CompteDto findByNumero(String numero);
    CompteDto save(CompteDto compteDto);
    CompteDto update(CompteDto compteDto,Long id);

    CompteDto delete(CompteDto compteDto, Long id);
}
