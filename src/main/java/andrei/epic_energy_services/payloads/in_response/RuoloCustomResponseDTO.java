package andrei.epic_energy_services.payloads.in_response;

import andrei.epic_energy_services.entities.RuoloCustom;

import java.util.UUID;

public class RuoloCustomResponseDTO {

    private final UUID idRuoloCustom;
    private final String ruoloCustom;

    public RuoloCustomResponseDTO(RuoloCustom ruoloCustom) {
        this.idRuoloCustom = ruoloCustom.getIdRuoloCustom();
        this.ruoloCustom = ruoloCustom.getRuoloCustom();
    }

    public String getRuoloCustom() {
        return ruoloCustom;
    }

    public UUID getIdRuoloCustom() {
        return idRuoloCustom;
    }
}
