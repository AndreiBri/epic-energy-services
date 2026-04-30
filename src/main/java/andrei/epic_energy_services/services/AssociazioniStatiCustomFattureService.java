package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.AssociazioneStatoCustomFattura;
import andrei.epic_energy_services.repositories.AssociazioneStatoCustomFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociazioniStatiCustomFattureService {

    @Autowired
    private AssociazioneStatoCustomFatturaRepository associazioniStatoCustomRepo;

    public List<AssociazioneStatoCustomFattura> findAll() {
        return associazioniStatoCustomRepo.findAll();
    }
}
