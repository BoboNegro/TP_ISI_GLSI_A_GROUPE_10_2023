package frey.house.ega.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="numero", unique=true)
    private String numero;
    @Column(name = "dateCreation")
    private Date dateCreation;
    @Column(name = "solde")
    private BigDecimal solde;
    @Column(name = "etat")
    private boolean etat = true;

    @Column(name = "typeCompte")
    @Enumerated(EnumType.STRING)
    private TypeCompte type;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client proprietaire;

    public enum TypeCompte {
        EPARGNE, COURANT}
}

