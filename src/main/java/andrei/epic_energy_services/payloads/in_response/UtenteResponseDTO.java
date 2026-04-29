package andrei.epic_energy_services.payloads.in_response;

import andrei.epic_energy_services.entities.Utente;
import andrei.epic_energy_services.enums.RuoloUtente;

import java.util.UUID;

public class UtenteResponseDTO {

    private final UUID idUtente;
    private final String username;
    private final String email;
    private final String nome;
    private final String cognome;
    private final String avatarUrl;
    private final RuoloUtente ruolo;

    public UtenteResponseDTO(Utente utente) {
        this.idUtente = utente.getIdUtente();
        this.username = utente.getUsername();
        this.email = utente.getEmail();
        this.nome = utente.getNome();
        this.cognome = utente.getCognome();
        this.avatarUrl = utente.getAvatarUrl();
        this.ruolo = utente.getRuolo();
    }

    public UUID getIdUtente() {
        return idUtente;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public RuoloUtente getRuolo() {
        return ruolo;
    }
}
