package andrei.epic_energy_services.payloads.in_response;

import andrei.epic_energy_services.enums.FormaGiuridica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ClienteDaMandareDTO(
        UUID idCliente,
        String ragioneSociale,
        String partitaIva,
        String email,
        String pec,
        String telefono,
        String emailContatto,
        String nomeContatto,
        String cognomeContatto,
        String telefonoContatto,
        BigDecimal fatturatoAnnuale,
        String logoAziendaleUrl,
        FormaGiuridica formaGiuridica,
        LocalDate dataInserimento,
        LocalDate DataUltimoContatto
) {
}
