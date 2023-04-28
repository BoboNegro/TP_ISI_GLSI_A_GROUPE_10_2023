package frey.house.ega.servicesImplementations;

import frey.house.ega.dto.VersementDto;
import frey.house.ega.exceptions.ResourceNotFoundException;
import frey.house.ega.mappers.EntityMapper;
import frey.house.ega.model.Compte;
import frey.house.ega.model.Versement;
import frey.house.ega.repositories.CompteRepository;
import frey.house.ega.repositories.VersementRepository;
import frey.house.ega.services.VersementInterface;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VersementService implements VersementInterface {
    private final EntityMapper entityMapper;
    private final CompteRepository compteRepository;
    private final VersementRepository versementRepository;

    public VersementService(EntityMapper entityMapper, CompteRepository compteRepository, VersementRepository versementRepository) {
        this.entityMapper = entityMapper;
        this.compteRepository = compteRepository;
        this.versementRepository = versementRepository;
    }

    @Override
    public List<VersementDto> findAll() {
        List<Versement> versements = this.versementRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return versements.stream().map(this.entityMapper::VersementToVersementDto).collect(Collectors.toList());
    }

    @Override
    public VersementDto findById(Long id) {
        Versement versement = this.versementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("versement","id",id));
        return this.entityMapper.VersementToVersementDto(versement);
    }

    @Override
    public VersementDto save(VersementDto VersementDto) {
        Versement versement = this.entityMapper.VersementDtoToVersement(VersementDto);
        Versement savedversement = this.versementRepository.save(versement);
        return this.entityMapper.VersementToVersementDto(savedversement);
    }

    @Override
    public List<VersementDto> findByCompteCible(String numero) {
        Compte compte = this.compteRepository.findByNumero(numero);
        List<Versement> versements = this.versementRepository.findByCompteCibleOrderByDateVersementDesc(compte);
        return versements.stream().map(this.entityMapper::VersementToVersementDto).collect(Collectors.toList());
    }

    @Override
    public List<VersementDto> findByClient(Long id) {
        List<Versement> versements = this.versementRepository.findByClient(id);
        return versements.stream().map(this.entityMapper::VersementToVersementDto).collect(Collectors.toList());
    }


}
