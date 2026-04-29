package andrei.epic_energy_services.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "stati_custom_fatture")
@Getter
@NoArgsConstructor
public class StatoCustomFattura {

    @Id
    @GeneratedValue
    @Column(name = "id_stato_custom")
    private UUID idStatoCustom;

    @Column(name = "stato_custom", unique = true, nullable = false)
    private String statoCustom;

    public StatoCustomFattura(String statoCustom) {
        this.statoCustom = statoCustom.toLowerCase();
    }
}