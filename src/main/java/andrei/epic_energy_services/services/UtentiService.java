package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.Utente;
import andrei.epic_energy_services.enums.RuoloUtente;
import andrei.epic_energy_services.repositories.RuoliCustomRepository;
import andrei.epic_energy_services.repositories.UtentiRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UtentiService {

    private final UtentiRepository utenteRepository;
    private final RuoliCustomRepository ruoloCustomRepository;
    private final PasswordEncoder passwordEncoder;

    public UtentiService(UtentiRepository utenteRepository, RuoliCustomRepository ruoloCustomRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.ruoloCustomRepository = ruoloCustomRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    public Utente findById(UUID id) {
        return utenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }

    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }

    public Utente create(Utente utente) {

        if (utenteRepository.existsByUsername(utente.getUsername())) {
            throw new RuntimeException("Username già in uso");
        }

        if (utenteRepository.existsByEmail(utente.getEmail())) {
            throw new RuntimeException("Email già in uso");
        }

        utente.setPassword(passwordEncoder.encode(utente.getPassword()));

        if (utente.getRuolo() == null) {
            utente.setRuolo(RuoloUtente.USER);
        }

        return utenteRepository.save(utente);
    }

    public Utente update(UUID id, Utente updated) {

        Utente utente = findById(id);

        utente.setUsername(updated.getUsername());
        utente.setEmail(updated.getEmail());
        utente.setNome(updated.getNome());
        utente.setCognome(updated.getCognome());
        utente.setAvatarUrl(updated.getAvatarUrl());

        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            utente.setPassword(passwordEncoder.encode(updated.getPassword()));
        }

        utente.setRuolo(updated.getRuolo());

        return utenteRepository.save(utente);
    }

    public void delete(UUID id) {
        Utente utente = findById(id);
        utenteRepository.delete(utente);
    }
}
