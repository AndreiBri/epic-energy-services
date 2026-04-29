package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.RuoloCustom;
import andrei.epic_energy_services.entities.Utente;
import andrei.epic_energy_services.entities.UtenteRuoloCustom;
import andrei.epic_energy_services.exceptions.NotFoundException;
import andrei.epic_energy_services.repositories.RuoliCustomRepository;
import andrei.epic_energy_services.repositories.UtenteRuoloCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RuoliCustomService {
    
    @Autowired
    private UtentiService utentiService;
    
    
    @Autowired
    private UtenteRuoloCustomRepository utenteRuoloCustomRepository;

    private final RuoliCustomRepository ruoloCustomRepository;

    public RuoliCustomService(RuoliCustomRepository ruoloCustomRepository) {
        this.ruoloCustomRepository = ruoloCustomRepository;
    }

    public List<RuoloCustom> findAll() {
        return ruoloCustomRepository.findAll();
    }

    public RuoloCustom findById(UUID id) {
        return ruoloCustomRepository.findById(id).orElseThrow(() -> new NotFoundException("Ruolo non trovato"));
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
    
    

    /**
     * Aggiungi un'associazione tra ruolo custom e utente.
     */
    public void aggiungiAssociazioneTraRuoloUtenteEUtente(UUID ruoloCustomId, UUID utenteId) throws NotFoundException
    {
        // cosa significa aggiungere un'associazione tra ruolo utente e utente?
        // abbiamo fatto un'entità che associa ruolo utente e utente
        // aggiungere questo oggetto in DB
        // come lo creo l'oggetto?    
        
        // prima cerco l'utente per vedere se esiste
        
        Utente utente = this.utentiService.findById(utenteId);
        RuoloCustom ruoloCustom = this.findById(ruoloCustomId);
        
        UtenteRuoloCustom utenteRuoloCustom = new UtenteRuoloCustom(utente, ruoloCustom);
        
        this.utenteRuoloCustomRepository.save(utenteRuoloCustom);
    }
    
    
}
