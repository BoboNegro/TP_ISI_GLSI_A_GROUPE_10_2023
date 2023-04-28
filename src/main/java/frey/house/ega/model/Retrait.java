package frey.house.ega.model;

import frey.house.ega.dto.CompteDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Retrait {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dateRetrait")
    private Date dateRetrait;
    @Column(name = "montant")
    private BigDecimal montant;

    @ManyToOne
    @JoinColumn(name = "compte_cible_id")
    private Compte compteCible;
}
