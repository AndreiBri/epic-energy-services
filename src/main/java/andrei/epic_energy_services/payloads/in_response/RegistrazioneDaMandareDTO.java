package andrei.epic_energy_services.payloads.in_response;

import andrei.epic_energy_services.entities.Utente;

import java.util.UUID;

public class RegistrazioneDaMandareDTO {
    private final UUID utenteId;

    public RegistrazioneDaMandareDTO(Utente utente) {
        this.utenteId = utente.getIdUtente();
    }

    public UUID getUtenteId() {
        return utenteId;
    }
}