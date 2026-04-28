package andrei.epic_energy_services.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "utenti_ruoli_custom")
@Getter
@Setter
@NoArgsConstructor
public class UtenteRuoloCustom {

    @Id
    @GeneratedValue
    @Column(name = "id_utente_ruolo_custom")
    @Setter(AccessLevel.NONE)
    private UUID idUtenteRuoloCustom;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_ruolo_custom")
    private RuoloCustom ruoloCustom;

    public UtenteRuoloCustom(Utente utente, RuoloCustom ruoloCustom) {
        this.utente = utente;
        this.ruoloCustom = ruoloCustom;
    }
}
