package frey.house.ega.servicesImplementations;

import frey.house.ega.dto.VirementDto;
import frey.house.ega.exceptions.ResourceNotFoundException;
import frey.house.ega.mappers.EntityMapper;
import frey.house.ega.model.Compte;
import frey.house.ega.model.Virement;
import frey.house.ega.repositories.CompteRepository;
import frey.house.ega.repositories.VirementRepository;
import frey.house.ega.services.VirementInterface;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirementService implements VirementInterface {

    private final EntityMapper entityMapper;
    private final CompteRepository compteRepository;
    private final VirementRepository virementRepository;

    public VirementService(EntityMapper entityMapper, CompteRepository compteRepository, VirementRepository virementRepository) {
        this.entityMapper = entityMapper;
        this.compteRepository = compteRepository;
        this.virementRepository = virementRepository;
    }


    @Override
    public List<VirementDto> findAll() {
        List<Virement> virements = this.virementRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return virements.stream().map(this.entityMapper::VirementToVirementDto).collect(Collectors.toList());
    }

    @Override
    public VirementDto findById(Long id) {
        Virement virement = this.virementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("virement","id",id));
        return this.entityMapper.VirementToVirementDto(virement);
    }

    @Override
    public VirementDto save(VirementDto VirementDto) {
        Virement virement = this.entityMapper.VirementDtoToVirement(VirementDto);
        virement.setDateExecution(new Date());
        Virement savedvirement = this.virementRepository.save(virement);
        return this.entityMapper.VirementToVirementDto(savedvirement);
    }

    @Override
    public List<VirementDto> findByCompteDebite(String numero) {
        Compte compte = this.compteRepository.findByNumero(numero);
        List<Virement> virements = this.virementRepository.findByCompteDebiteOrderByDateExecutionDesc(compte);
        return virements.stream().map(this.entityMapper::VirementToVirementDto).collect(Collectors.toList());
    }

    @Override
    public List<VirementDto> findByCompteCredite(String numero) {
        Compte compte = this.compteRepository.findByNumero(numero);
        List<Virement> virements = this.virementRepository.findByCompteCrediteOrderByDateExecutionDesc(compte);
        return virements.stream().map(this.entityMapper::VirementToVirementDto).collect(Collectors.toList());
    }

    @Override
    public List<VirementDto> findByClientCrediteur(Long id) {
        List<Virement> virements = this.virementRepository.findByClientCrediteur(id);
        return virements.stream().map(this.entityMapper::VirementToVirementDto).collect(Collectors.toList());
    }

    @Override
    public List<VirementDto> findByClientDebiteur(Long id) {
        List<Virement> virements = this.virementRepository.findByClientDebiteur(id);
        return virements.stream().map(this.entityMapper::VirementToVirementDto).collect(Collectors.toList());
    }
}
