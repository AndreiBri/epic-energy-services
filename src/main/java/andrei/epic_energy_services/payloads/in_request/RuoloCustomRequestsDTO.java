package andrei.epic_energy_services.payloads.in_request;

import jakarta.validation.constraints.NotBlank;

public record RuoloCustomRequestsDTO(

        @NotBlank(message = "Nome ruolo obbligatorio")
        String ruoloCustom
) {
}
