package andrei.epic_energy_services.entities;

import andrei.epic_energy_services.enums.TipoSede;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(
        name = "sedi",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_cliente_tipo_sede",
                        columnNames = {"id_cliente", "tipo_sede"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sede")
    private UUID idSede;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_sede", nullable = false)
    private TipoSede tipoSede;

    @ManyToOne
    @JoinColumn(name = "id_comune", nullable = false)
    private Comune comune;

    @Column(nullable = false)
    private String via;

    @Column(nullable = false)
    private String civico;

    private String localita;

    @Column(nullable = false)
    private String cap;
}