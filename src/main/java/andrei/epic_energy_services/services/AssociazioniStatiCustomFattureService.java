package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.AssociazioneStatoCustomFattura;
import andrei.epic_energy_services.entities.Fattura;
import andrei.epic_energy_services.entities.StatoCustomFattura;
import andrei.epic_energy_services.payloads.in_request.AssociazioneStatoCustomFatturaMandatoDTO;
import andrei.epic_energy_services.repositories.AssociazioneStatoCustomFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociazioniStatiCustomFattureService {

    @Autowired
    private AssociazioneStatoCustomFatturaRepository associazioniStatoCustomRepo;

    @Autowired
    private StatiCustomFatturaService statiCustomFatturaService;

    @Autowired
    private FattureService fattureService;

    public List<AssociazioneStatoCustomFattura> findAll() {
        return associazioniStatoCustomRepo.findAll();
    }

    public AssociazioneStatoCustomFattura save(AssociazioneStatoCustomFatturaMandatoDTO dto) {

        StatoCustomFattura statoCustomFatturaFromDB = statiCustomFatturaService.getById(dto.idStatoCustom());
        Fattura fatturaFromDb = fattureService.getFatturaById(dto.idFattura());

        AssociazioneStatoCustomFattura associazioneFatturaStato = new AssociazioneStatoCustomFattura(
//                sto cercando di passargli entita con il loro id che abbiamo ricevuto dalla richiesta del cliente
                fatturaFromDb, statoCustomFatturaFromDB
        );


        return associazioniStatoCustomRepo.save(associazioneFatturaStato);
    }

}
