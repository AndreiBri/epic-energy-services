package andrei.epic_energy_services.payloads.in_request;

import andrei.epic_energy_services.enums.FormaGiuridica;
import java.math.BigDecimal;

// DTO per l'aggiornamento parziale del cliente - tutti i campi sono opzionali
public record AggiornaClienteDTO(
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
        FormaGiuridica formaGiuridica
) {}
