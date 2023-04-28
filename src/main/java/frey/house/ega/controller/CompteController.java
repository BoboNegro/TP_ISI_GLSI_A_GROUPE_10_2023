package frey.house.ega.controller;

import frey.house.ega.dto.CompteDto;
import frey.house.ega.servicesImplementations.ClientService;
import frey.house.ega.servicesImplementations.CompteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comptes")
public class CompteController {



    private final CompteService compteService;
    private final ClientService clientService;

    public CompteController(CompteService compteService, ClientService clientService) {
        this.compteService = compteService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.compteService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{numero}")
    public ResponseEntity<?> findByNumero(@PathVariable("numero") String numero) {
        CompteDto compteDto = this.compteService.findByNumero(numero);
        return new ResponseEntity<CompteDto>(compteDto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody CompteDto compteDto) {

        if (compteDto == null){
            return new ResponseEntity<>("Données requises manquantes!", HttpStatus.BAD_REQUEST);
        }
        compteDto.setProprietaire(this.clientService.findById(compteDto.getProprietaire().getId()));
        CompteDto createdCompte = this.compteService.save(compteDto);
        return new ResponseEntity<>(createdCompte, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CompteDto compteDto,
                                    @PathVariable("id") Long id) {
        if (compteDto == null) {
            return new ResponseEntity<>("Données requises manquantes!", HttpStatus.BAD_REQUEST);
        }
        CompteDto updated = this.compteService.update(compteDto, id);
        return new ResponseEntity<CompteDto>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestBody CompteDto compteDto,
                                    @PathVariable("id") Long id) {
        if (compteDto == null) {
            return new ResponseEntity<>("Données requises manquantes!", HttpStatus.BAD_REQUEST);
        }
        CompteDto updated = this.compteService.delete(compteDto, id);
        return new ResponseEntity<CompteDto>(updated, HttpStatus.OK);
    }












}