package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.StatoCustomFattura;
import andrei.epic_energy_services.exceptions.InvalidDataException;
import andrei.epic_energy_services.exceptions.NotFoundException;
import andrei.epic_energy_services.repositories.StatoCustomFatturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatiCustomFatturaService {

    private final StatoCustomFatturaRepository statoRepository;

    public List<StatoCustomFattura> getAll() {
        return statoRepository.findAll();
    }

    public StatoCustomFattura getById(UUID idStato) {
        return statoRepository.findById(idStato)
                .orElseThrow(() -> new NotFoundException("Stato con id " + idStato + " non trovato"));
    }

    public StatoCustomFattura create(String statoCustom) {
        if (statoRepository.existsByStatoCustom(statoCustom.toLowerCase())) {
            throw new InvalidDataException("Stato '" + statoCustom + "' esiste già");
        }
        return statoRepository.save(new StatoCustomFattura(statoCustom));
    }

    public StatoCustomFattura update(UUID idStato, String statoCustom) {
        StatoCustomFattura stato = getById(idStato);
        if (statoRepository.existsByStatoCustom(statoCustom.toLowerCase())) {
            throw new InvalidDataException("Stato '" + statoCustom + "' esiste già");
        }
        stato.setStatoCustom(statoCustom.toLowerCase());
        return statoRepository.save(stato);
    }

    public void delete(UUID idStato) {
        StatoCustomFattura stato = getById(idStato);
        statoRepository.delete(stato);
    }
}