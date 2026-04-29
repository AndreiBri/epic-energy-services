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

    protected UtenteRuoloCustom() {}
    
    public UtenteRuoloCustom(Utente utente, RuoloCustom ruoloCustom) {
        this.utente = utente;
        this.ruoloCustom = ruoloCustom;
    }

    public UUID getIdUtenteRuoloCustom() {
        return idUtenteRuoloCustom;
    }

    public RuoloCustom getRuoloCustom() {
        return ruoloCustom;
    }

    public Utente getUtente() {
        return utente;
    }
}
