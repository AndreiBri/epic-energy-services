package andrei.epic_energy_services.payloads.in_response;

import andrei.epic_energy_services.entities.Comune;

import java.util.UUID;

public class ComuneDaMandareDTO {
    private final UUID idComune;
    private final UUID idProvincia;
    private final String nomeComune;
    
    public ComuneDaMandareDTO(Comune comune) {
        this.idComune = comune.getIdComune();
        this.idProvincia = comune.getProvincia().getIdProvincia();
        this.nomeComune = comune.getNomeComune();
    }

    public UUID getIdComune() {
        return idComune;
    }

    public UUID getIdProvincia() {
        return idProvincia;
    }

    public String getNomeComune() {
        return nomeComune;
    }
}
