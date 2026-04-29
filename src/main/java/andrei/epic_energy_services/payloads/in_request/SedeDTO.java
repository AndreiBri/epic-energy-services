package andrei.epic_energy_services.payloads.in_request;

import andrei.epic_energy_services.enums.TipoSede;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record SedeDTO(
        @NotNull UUID idComune,
        @NotNull TipoSede tipoSede,
        @NotBlank String via,
        @NotBlank String civico,
        String localita,
        @NotBlank String cap
) {}