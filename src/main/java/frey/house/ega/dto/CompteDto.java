package frey.house.ega.dto;

import frey.house.ega.model.Compte;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompteDto {

    private Long id;
    private String numero;
    private Date dateCreation;
    private BigDecimal solde;
    private boolean etat = true;

    @Enumerated(EnumType.STRING)
    private Compte.TypeCompte type;

    private ClientDto proprietaire;

    public enum TypeCompte {
        EPARGNE, COURANT}
}
