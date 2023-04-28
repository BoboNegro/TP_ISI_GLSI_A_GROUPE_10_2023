package frey.house.ega.servicesImplementations;


import frey.house.ega.dto.ClientDto;
import frey.house.ega.dto.CompteDto;
import frey.house.ega.exceptions.ResourceNotFoundException;
import frey.house.ega.mappers.EntityMapper;
import frey.house.ega.model.Client;
import frey.house.ega.model.Compte;
import frey.house.ega.repositories.CompteRepository;
import frey.house.ega.services.CompteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompteService implements CompteInterface {

    @Autowired
    private GenerateNumeroService generateNumeroService;

    private final EntityMapper entityMapper;
    private final CompteRepository compteRepository;

    public CompteService(EntityMapper entityMapper, CompteRepository compteRepository) {
        this.entityMapper = entityMapper;
        this.compteRepository = compteRepository;
    }

    @Override
    public List<CompteDto> findAll() {
        List<Compte> comptes = this.compteRepository.findCompteByEtatOrderByIdDesc(true);
        return comptes.stream().map(this.entityMapper::CompteToCompteDto).collect(Collectors.toList());
    }

    @Override
    public CompteDto findById(Long id) {
        Compte compte = this.compteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("compte","id",id));
        return this.entityMapper.CompteToCompteDto(compte);
    }


    @Override
    public CompteDto findByNumero(String numero) {
        Compte compte = this.compteRepository.findByNumero(numero);
        return this.entityMapper.CompteToCompteDto(compte);
    }

    @Override
    public CompteDto save(CompteDto CompteDto) {
        Date date = new Date();
        String numeroCompte = GenerateNumeroService.generateAccountNumber(new Date());
        Compte compte = this.entityMapper.CompteDtoToCompte(CompteDto);
        compte.setNumero(numeroCompte);
        compte.setDateCreation(date);
        System.out.println(date);
        compte.setSolde(BigDecimal.ZERO);

        Compte savedcompte = this.compteRepository.save(compte);
        return this.entityMapper.CompteToCompteDto(savedcompte);
    }

    @Override
    public CompteDto update(CompteDto compteDto, Long id) {
        Compte compte = this.compteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Compte", "id", id));
        compte = this.entityMapper.CompteDtoToCompte(compteDto);
        Compte updatedCompte = this.compteRepository.save(compte);
        return this.entityMapper.CompteToCompteDto(updatedCompte);
    }

    @Override
    public CompteDto delete(CompteDto compteDto, Long id){
        Compte compte = this.compteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Client", "id", id));
        compteDto.setEtat(false);
        compte = this.entityMapper.CompteDtoToCompte(compteDto);
        Compte updateCompte = this.compteRepository.save(compte);
        return this.entityMapper.CompteToCompteDto(updateCompte);
    }

    
}
