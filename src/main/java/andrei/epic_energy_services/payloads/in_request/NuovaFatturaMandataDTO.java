package andrei.epic_energy_services.payloads.in_request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record NuovaFatturaMandataDTO(
        @NotNull UUID idCliente,
        @NotNull LocalDate dataCreazione,
        @NotNull BigDecimal importo,
        @NotNull Integer numero
) {
}