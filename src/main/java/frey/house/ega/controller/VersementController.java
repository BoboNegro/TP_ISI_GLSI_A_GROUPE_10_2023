package frey.house.ega.controller;

import frey.house.ega.dto.CompteDto;
import frey.house.ega.dto.VersementDto;
import frey.house.ega.dto.VersementDto;
import frey.house.ega.servicesImplementations.CompteService;
import frey.house.ega.servicesImplementations.VersementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/versements")
public class VersementController {

    private final VersementService versementService;
    private final CompteService compteService;

    public VersementController(VersementService versementService, CompteService compteService) {
        this.versementService = versementService;
        this.compteService = compteService;
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.versementService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/compte/{numero}")
    public ResponseEntity<?> findByCompteCible(@PathVariable("numero") String numero) {
        List<VersementDto> versementDto = this.versementService.findByCompteCible(String.valueOf(numero));
        return new ResponseEntity<>(versementDto, HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<?> findByClient(@PathVariable("id") Long id) {
        List<VersementDto> versementDto = this.versementService.findByClient(id);
        return new ResponseEntity<>(versementDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        VersementDto versementDto = this.versementService.findById(id);
        return new ResponseEntity<VersementDto>(versementDto, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<?> faireVersement(@RequestBody VersementDto versementDto) {

        if (versementDto == null) {
            return new ResponseEntity<>("Données requises manquantes!", HttpStatus.BAD_REQUEST);
        } else if (versementDto.getMontant().compareTo(BigDecimal.ZERO) < 0) {
            return new ResponseEntity<>("Le montant doit être positif!", HttpStatus.BAD_REQUEST);
        }
        CompteDto compteDto = compteService.findById(versementDto.getCompteCible().getId());
        if (compteDto == null) {
            return new ResponseEntity<>("Compte non trouvé!", HttpStatus.NOT_FOUND);
        }


        compteDto.setSolde(compteDto.getSolde().add(versementDto.getMontant()));
        compteService.update(compteDto, compteDto.getId());

        versementDto.setCompteCible(compteDto);
        versementDto.setDateVersement(new Date());

        VersementDto saved = versementService.save(versementDto);


        return new ResponseEntity<>("Versement effectué avec succès!", HttpStatus.OK);
    }
}
