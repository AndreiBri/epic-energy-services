package andrei.epic_energy_services.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "ruoli_custom")
@Getter
@NoArgsConstructor
public class RuoloCustom {

    @Id
    @GeneratedValue
    @Column(name = "id_ruolo_custom")
    private UUID idRuoloCustom;

    @Column(name = "ruolo_custom", unique = true)
    private String ruoloCustom;

    public RuoloCustom(String ruoloCustom) {
        this.ruoloCustom = ruoloCustom.toLowerCase();
    }
}
