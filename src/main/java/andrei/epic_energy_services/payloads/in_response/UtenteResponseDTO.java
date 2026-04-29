package andrei.epic_energy_services.payloads.in_response;

import andrei.epic_energy_services.enums.RuoloUtente;

import java.util.UUID;

public record UtenteResponseDTO(

        UUID idUtente,
        String username,
        String email,
        String nome,
        String cognome,
        String avatarUrl,
        RuoloUtente ruolo
) {
}
