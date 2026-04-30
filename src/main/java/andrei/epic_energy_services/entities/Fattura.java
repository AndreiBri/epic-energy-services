package andrei.epic_energy_services.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fatture")
@Getter
@Setter
@NoArgsConstructor
public class Fattura {

    @Id
    @GeneratedValue
    @Column(name = "id_fattura")
    @Setter(AccessLevel.NONE)
    private UUID idFattura;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "data_creazione", nullable = false)
    private LocalDate dataCreazione;

    @Column(nullable = false)
    private BigDecimal importo;

    @Column(nullable = false)
    private Integer numero;

    @OneToMany(mappedBy = "fattura")
    private List<AssociazioneStatoCustomFattura> associazioniStati;

    public Fattura(Cliente cliente, LocalDate dataCreazione, BigDecimal importo, Integer numero) {
        this.cliente = cliente;
        this.dataCreazione = dataCreazione;
        this.importo = importo;
        this.numero = numero;
    }
}