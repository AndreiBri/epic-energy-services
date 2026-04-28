package andrei.epic_energy_services.entities;

import andrei.epic_energy_services.enums.RuoloUtente;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
public class Utente {

    @Id
    @GeneratedValue
    @Column(name = "id_utente")
    @Setter(AccessLevel.NONE)
    private UUID idUtente;

    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(name = "avatar_url", nullable = false)
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RuoloUtente ruolo;

    
    private Set<RuoloCustom> ruoliCustom;

    public Utente(String username, String email, String password, String nome, String cognome, String avatarUrl, RuoloUtente ruolo, Set<RuoloCustom> ruoliCustom) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.avatarUrl = avatarUrl;
        this.ruolo = ruolo;
        this.ruoliCustom = ruoliCustom;
    }
}
