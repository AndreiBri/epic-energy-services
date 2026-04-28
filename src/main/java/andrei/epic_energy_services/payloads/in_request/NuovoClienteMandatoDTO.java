package andrei.epic_energy_services.payloads.in_request;

import andrei.epic_energy_services.enums.FormaGiuridica;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NuovoClienteMandatoDTO(
        @NotBlank(message = "ragioneSociale è obbligatoria") String ragioneSociale,
        @NotBlank(message = "partitaIva è obbligatoria") String partitaIva,
        @NotBlank(message = "email aziendale è obbligatoria") @Email(message = "email non valida") String email,
        @NotBlank(message = "pec è obbligatoria") String pec,
        @NotBlank(message = "telefono è obbligatorio") String telefono,

        @NotBlank(message = "email personale è obbligatorio") @Email(message = "email non valida") String emailContatto,
        @NotBlank(message = "nome del cliente è obbligatorio") String nomeContatto,
        @NotBlank(message = "cognome del cliente è obbligatorio") String cognomeContatto,
        @NotBlank(message = "telefono per contattare cliente  è obbligatorio") String telefonoContatto,
        @NotBlank(message = "media fatturato è obbligatorio") BigDecimal fatturatoAnnuale,
        @NotNull(message = "Scegli una forma giuridica tra : PA,SPA,SRLS,SRL,SAS") FormaGiuridica formaGiuridica
) {
}
