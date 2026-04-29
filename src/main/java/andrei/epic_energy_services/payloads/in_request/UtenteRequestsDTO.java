package andrei.epic_energy_services.payloads.in_request;

import andrei.epic_energy_services.enums.RuoloUtente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UtenteRequestsDTO(
        @NotBlank(message = "Username obbligatorio")
        String username,

        @Email(message = "Email non valida")
        @NotBlank(message = "Email obbligatoria")
        String email,

        @NotBlank(message = "Password obbligatoria")
        String password,

        @NotBlank(message = "Nome obbligatorio")
        String nome,

        @NotBlank(message = "Cognome obbligatorio")
        String cognome,

        @NotBlank(message = "Avatar obbligatorio")
        String avatarUrl,

        RuoloUtente ruolo
) {
}
