package andrei.epic_energy_services.payloads.in_request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UtenteRuoloCustomRequestDTO(

        @NotNull(message = "Id utente obbligatorio")
        UUID utenteId,

        @NotNull(message = "Id ruolo obbligatorio")
        UUID ruoloCustomId
) {
}
