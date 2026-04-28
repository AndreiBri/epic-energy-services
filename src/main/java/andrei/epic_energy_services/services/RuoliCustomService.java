package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.RuoloCustom;
import andrei.epic_energy_services.repositories.RuoliCustomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RuoliCustomService {

    private final RuoliCustomRepository ruoloCustomRepository;

    public RuoliCustomService(RuoliCustomRepository ruoloCustomRepository) {
        this.ruoloCustomRepository = ruoloCustomRepository;
    }

    public List<RuoloCustom> findAll() {
        return ruoloCustomRepository.findAll();
    }

    public RuoloCustom findById(UUID id) {
        return ruoloCustomRepository.findById(id).orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
    }

    public RuoloCustom findByNome(String nome) {
        return ruoloCustomRepository.findByRuoloCustom(nome).orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
    }

    public RuoloCustom create(RuoloCustom ruolo) {

        if (ruoloCustomRepository.existsByRuoloCustom(ruolo.getRuoloCustom())) {
            throw new RuntimeException("Ruolo già esistente");
        }

        return ruoloCustomRepository.save(ruolo);
    }


}
