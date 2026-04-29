package andrei.epic_energy_services.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ruoli_custom")
public class RuoloCustom {

    @Id
    @GeneratedValue
    @Column(name = "id_ruolo_custom")
    private UUID idRuoloCustom;

    @Column(name = "ruolo_custom", unique = true)
    private String ruoloCustom;

    protected RuoloCustom() {

    }

    public RuoloCustom(String ruoloCustom) {
        this.ruoloCustom = ruoloCustom.toLowerCase();
    }

    public UUID getIdRuoloCustom() {
        return idRuoloCustom;
    }

    public String getRuoloCustom() {
        return ruoloCustom;
    }
}
