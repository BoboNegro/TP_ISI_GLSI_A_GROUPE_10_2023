package frey.house.ega.model;

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
public class Virement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compte_debite_id")
    private Compte compteDebite;

    @ManyToOne
    @JoinColumn(name = "compte_credite_id")
    private Compte compteCredite;

    @Column(name = "montant", nullable = false)
    private BigDecimal montant;

    @Column(name = "dateExecution", nullable = false)
    private Date dateExecution;
}
