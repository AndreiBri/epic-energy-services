package andrei.epic_energy_services.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "associazioni_stati_custom_fatture",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id_fattura", "id_stato_custom"})
        }
)
public class AssociazioneStatoCustomFattura {

    @Id
    @GeneratedValue
    @Column(name = "id_associazione")
    private UUID idAssociazione;

    @ManyToOne
    @JoinColumn(name = "id_fattura", nullable = false)
    private Fattura fattura;

    @ManyToOne
    @JoinColumn(name = "id_stato_custom", nullable = false)
    private StatoCustomFattura statoCustomFattura;

    public AssociazioneStatoCustomFattura(Fattura fattura, StatoCustomFattura statoCustomFattura) {
        this.fattura = fattura;
        this.statoCustomFattura = statoCustomFattura;
    }

    protected AssociazioneStatoCustomFattura() {

    }

    public UUID getIdAssociazione() {
        return idAssociazione;
    }

    public Fattura getFattura() {
        return fattura;
    }

    public StatoCustomFattura getStatoCustomFattura() {
        return statoCustomFattura;
    }

}