package andrei.epic_energy_services.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ruoli_custom")
@Getter
@Setter
@NoArgsConstructor
public class RuoloCustom {

    @Id
    @GeneratedValue
    @Column(name = "id_ruolo_custom")
    @Setter(AccessLevel.NONE)
    private UUID idRuoloCustom;

    @Column(name = "ruolo_custom", unique = true)
    private String ruoloCustom;

    @ManyToMany(mappedBy = "ruoliCustom")
    private Set<Utente> utenti;

    public RuoloCustom(String ruoloCustom, Set<Utente> utenti) {
        this.ruoloCustom = ruoloCustom;
        this.utenti = utenti;
    }
}
