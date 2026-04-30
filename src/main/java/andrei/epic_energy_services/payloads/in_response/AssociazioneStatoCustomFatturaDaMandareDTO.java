package andrei.epic_energy_services.payloads.in_response;

import andrei.epic_energy_services.entities.AssociazioneStatoCustomFattura;
import andrei.epic_energy_services.entities.Fattura;

import java.util.UUID;

public class AssociazioneStatoCustomFatturaDaMandareDTO {

    private final UUID idStatoCustom;
    private final UUID idFattura;
    private final Fattura fattura;


    public AssociazioneStatoCustomFatturaDaMandareDTO(AssociazioneStatoCustomFattura associazioneStatoCustom) {

        this.idStatoCustom = associazioneStatoCustom.getStatoCustomFattura().getIdStatoCustom();
        this.idFattura = associazioneStatoCustom.getFattura().getIdFattura();
        this.fattura = associazioneStatoCustom.getFattura();

    }

    public UUID getIdStatoCustom() {
        return idStatoCustom;
    }

    public UUID getIdFattura() {
        return idFattura;
    }

    public Fattura getFattura() {
        return fattura;
    }
}
