package andrei.epic_energy_services.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "utenti_ruoli_custom",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id_utente", "id_ruolo_custom"})
        }
)
@Getter
@NoArgsConstructor
public class UtenteRuoloCustom {

    @Id
    @GeneratedValue
    @Column(name = "id_utente_ruolo_custom")
    private UUID idUtenteRuoloCustom;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_ruolo_custom", nullable = false)
    private RuoloCustom ruoloCustom;

    public UtenteRuoloCustom(Utente utente, RuoloCustom ruoloCustom) {
        this.utente = utente;
        this.ruoloCustom = ruoloCustom;
    }
}
