package frey.house.ega.servicesImplementations;

import frey.house.ega.dto.RetraitDto;
import frey.house.ega.exceptions.ResourceNotFoundException;
import frey.house.ega.mappers.EntityMapper;
import frey.house.ega.model.Compte;
import frey.house.ega.model.Retrait;
import frey.house.ega.repositories.CompteRepository;
import frey.house.ega.repositories.RetraitRepository;
import frey.house.ega.services.RetraitInterface;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetraitService implements RetraitInterface {
    private final EntityMapper entityMapper;

    private final CompteRepository compteRepository;
    private final RetraitRepository retraitRepository;

    public RetraitService(EntityMapper entityMapper, CompteRepository compteRepository, RetraitRepository retraitRepository) {
        this.entityMapper = entityMapper;
        this.compteRepository = compteRepository;
        this.retraitRepository = retraitRepository;
    }

    @Override
    public List<RetraitDto> findAll() {
        List<Retrait> retraits = this.retraitRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return retraits.stream().map(this.entityMapper::RetraitToRetraitDto).collect(Collectors.toList());
    }

    @Override
    public RetraitDto findById(Long id) {
        Retrait retrait = this.retraitRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("retrait","id",id));
        return this.entityMapper.RetraitToRetraitDto(retrait);
    }

    @Override
    public RetraitDto save(RetraitDto RetraitDto) {
        Retrait retrait = this.entityMapper.RetraitDtoToRetrait(RetraitDto);
        Retrait savedretrait = this.retraitRepository.save(retrait);
        return this.entityMapper.RetraitToRetraitDto(savedretrait);
    }

    @Override
    public List<RetraitDto> findByCompteCible(String numero) {
        Compte compte = this.compteRepository.findByNumero(numero);
        List<Retrait> retraits = this.retraitRepository.findAllByCompteCibleOrderByDateRetraitDesc(compte);
        return retraits.stream().map(this.entityMapper::RetraitToRetraitDto).collect(Collectors.toList());
    }

    @Override
    public List<RetraitDto> findByClient(Long id) {
        List<Retrait> retraits = this.retraitRepository.findByClient(id);
        return retraits.stream().map(this.entityMapper::RetraitToRetraitDto).collect(Collectors.toList());
    }

}
