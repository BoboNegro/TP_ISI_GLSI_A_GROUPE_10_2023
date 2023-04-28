package frey.house.ega.controller;

import frey.house.ega.dto.CompteDto;
import frey.house.ega.dto.RetraitDto;
import frey.house.ega.dto.VirementDto;
import frey.house.ega.model.Compte;
import frey.house.ega.servicesImplementations.CompteService;
import frey.house.ega.servicesImplementations.VirementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/virements")
public class VirementController {

    private final VirementService virementService;
    private final CompteService compteService;

    public VirementController(VirementService virementService, CompteService compteService) {
        this.virementService = virementService;
        this.compteService = compteService;
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.virementService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        VirementDto virementDto = this.virementService.findById(id);
        return new ResponseEntity<VirementDto>(virementDto, HttpStatus.OK);
    }

    @GetMapping("/debite/{numero}")
    public ResponseEntity<?> findByCompteDebite(@PathVariable("numero") String numero) {
        List<VirementDto> virementDto = this.virementService.findByCompteDebite(String.valueOf(numero));
        return new ResponseEntity<>(virementDto, HttpStatus.OK);
    }

    @GetMapping("/credite/{numero}")
    public ResponseEntity<?> findByCompteCredite(@PathVariable("numero") String numero) {
        List<VirementDto> virementDto = this.virementService.findByCompteCredite(String.valueOf(numero));
        return new ResponseEntity<>(virementDto, HttpStatus.OK);
    }

    @GetMapping("/crediteur/{id}")
    public ResponseEntity<?> findByClientCrediteur(@PathVariable("id") Long id) {
        List<VirementDto> virementDto = this.virementService.findByClientCrediteur(id);
        return new ResponseEntity<>(virementDto, HttpStatus.OK);
    }

    @GetMapping("/debiteur/{id}")
    public ResponseEntity<?> findByClientDebiteur(@PathVariable("id") Long id) {
        List<VirementDto> virementDto = this.virementService.findByClientDebiteur(id);
        return new ResponseEntity<>(virementDto, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<?> virement(@RequestBody VirementDto virementDto) {
        if (virementDto == null) {
            return new ResponseEntity<>("Données requises manquantes!", HttpStatus.BAD_REQUEST);
        }else if (virementDto.getMontant().compareTo(BigDecimal.ZERO) < 0) {
            return new ResponseEntity<>("Le montant doit être positif!", HttpStatus.BAD_REQUEST);
        }

        // Récupération des comptes à partir des IDs
        CompteDto source = compteService.findById(virementDto.getCompteDebite().getId());
        CompteDto destination = compteService.findById(virementDto.getCompteCredite().getId());

        if (source == null || destination == null) {
            return new ResponseEntity<>("Compte source ou compte destination introuvable!", HttpStatus.NOT_FOUND);
        }

        // Vérification que le solde du compte source est suffisant pour effectuer le virement
        if (source.getSolde().compareTo(virementDto.getMontant()) < 0) {
            return new ResponseEntity<>("Solde insuffisant!", HttpStatus.BAD_REQUEST);
        }

        // Mise à jour des soldes
        BigDecimal nouveauSoldeSource = source.getSolde().subtract(virementDto.getMontant());
        BigDecimal nouveauSoldeDestination = destination.getSolde().add(virementDto.getMontant());

        source.setSolde(nouveauSoldeSource);
        destination.setSolde(nouveauSoldeDestination);


        compteService.update(source, source.getId());
        compteService.update(destination, destination.getId());

        virementDto.setCompteCredite(source);
        virementDto.setCompteDebite(destination);

        VirementDto saved = virementService.save(virementDto);

        return new ResponseEntity<>("Virement effectué avec succès!", HttpStatus.OK);
    }




}
