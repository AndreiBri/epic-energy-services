package andrei.epic_energy_services.payloads.in_request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record AssociazioneStatoCustomFatturaMandatoDTO(
//        utente manda id della fattura con id dello stato da associare

        @NotBlank(message = "Fattura obbligatoria")
        UUID idFattura,

        @NotBlank(message = "Stato Fattura obbligatorio")
        UUID idStatoCustom

) {


}
