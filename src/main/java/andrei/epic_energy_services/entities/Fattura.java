package andrei.epic_energy_services.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name = "fatture",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_numero_fattura_per_cliente",
                        columnNames = {"id_cliente", "numero"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_fattura")
    private UUID idFattura;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "data_creazione", nullable = false)
    private LocalDate dataCreazione;

    @Column(nullable = false)
    private Long importo;

    @Column(nullable = false)
    private Integer numero;

    @Column(name = "stato_custom", nullable = false)
    private String statoCustom;
}