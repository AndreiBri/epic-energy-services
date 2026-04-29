package andrei.epic_energy_services.payloads.in_response;

import java.util.UUID;

public record UtenteRuoloCustomResponseDTO(

        UUID idUtenteRuoloCustom,
        UUID utenteId,
        UUID ruoloCustomId
) {
}
