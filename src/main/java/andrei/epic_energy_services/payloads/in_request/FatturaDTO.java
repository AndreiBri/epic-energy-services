package andrei.epic_energy_services.payloads.in_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FatturaDTO(
        @NotNull LocalDate dataCreazione,
        @NotNull BigDecimal importo,
        @NotNull Integer numero,
        @NotBlank String statoCustom
) {}