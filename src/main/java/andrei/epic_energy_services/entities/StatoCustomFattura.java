package andrei.epic_energy_services.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "stati_custom_fatture")
@Getter
@Setter
@NoArgsConstructor
public class StatoCustomFattura {

    @Id
    @GeneratedValue
    @Column(name = "id_stato_custom")
    @Setter(AccessLevel.NONE)
    private UUID idStatoCustom;

    @Column(name = "stato_custom", unique = true, nullable = false)
    private String statoCustom;

    public StatoCustomFattura(String statoCustom) {
        this.statoCustom = statoCustom.toLowerCase();
    }
}