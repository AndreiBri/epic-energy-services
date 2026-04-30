package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.AssociazioneStatoCustomFattura;
import andrei.epic_energy_services.entities.Cliente;
import andrei.epic_energy_services.entities.Fattura;
import andrei.epic_energy_services.entities.StatoCustomFattura;
import andrei.epic_energy_services.exceptions.NotFoundException;
import andrei.epic_energy_services.repositories.AssociazioneStatoCustomFatturaRepository;
import andrei.epic_energy_services.repositories.ClientiRepository;
import andrei.epic_energy_services.repositories.FattureRepository;
import andrei.epic_energy_services.repositories.StatoCustomFatturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FattureService {

    private final FattureRepository fattureRepository;
    private final ClientiRepository clientiRepository;
    private final StatoCustomFatturaRepository statoRepository;
    private final AssociazioneStatoCustomFatturaRepository associazioneRepository;

    public Page<Fattura> getAllFatture(UUID idCliente, String statoCustom, LocalDate data,
                                       Integer anno, BigDecimal importoMin, BigDecimal importoMax,
                                       Pageable pageable) {
        return fattureRepository.findWithFilters(
                idCliente, statoCustom, data, anno, importoMin, importoMax, pageable
        );
    }

    public Fattura getFatturaById(UUID idFattura) {
        return fattureRepository.findById(idFattura)
                .orElseThrow(() -> new NotFoundException("Fattura con id " + idFattura + " non trovata"));
    }

    public Fattura createFattura(UUID idCliente, LocalDate dataCreazione,
                                 BigDecimal importo, Integer numero) {

        Cliente cliente = clientiRepository.findById(idCliente)
                .orElseThrow(() -> new NotFoundException("Cliente con id " + idCliente + " non trovato"));

        Fattura fattura = new Fattura(cliente, dataCreazione, importo, numero);
        fattureRepository.save(fattura);

        return fattura;
    }

    public Fattura updateFattura(UUID idFattura, LocalDate dataCreazione,
                                 BigDecimal importo, Integer numero, String statoCustom) {

        Fattura fattura = getFatturaById(idFattura);
        fattura.setDataCreazione(dataCreazione);
        fattura.setImporto(importo);
        fattura.setNumero(numero);

        AssociazioneStatoCustomFattura associazione = associazioneRepository
                .findByFatturaIdFattura(idFattura)
                .orElseThrow(() -> new NotFoundException("Associazione stato non trovata per fattura " + idFattura));

        StatoCustomFattura nuovoStato = statoRepository.findByStatoCustom(statoCustom.toLowerCase())
                .orElseThrow(() -> new NotFoundException("Stato '" + statoCustom + "' non trovato"));

//        associazione.setStatoCustomFattura(nuovoStato);
        associazioneRepository.save(associazione);

        return fattureRepository.save(fattura);
    }

    public void deleteFattura(UUID idFattura) {
        Fattura fattura = getFatturaById(idFattura);
        associazioneRepository.findByFatturaIdFattura(idFattura)
                .ifPresent(associazioneRepository::delete);
        fattureRepository.delete(fattura);
    }
}