package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.VocePopolaDB;
import andrei.epic_energy_services.exceptions.PopolaDBHaTroppeVociException;
import andrei.epic_energy_services.repositories.PopolaDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PopolaDBService {
    
    @Autowired
    private PopolaDBRepository popolaDBRepository;

    /**
     * Ottieni tutte le voci della tabella popola_db.
     * Ci devono essere <= 1 voci.
     * 
     */
    public Optional<VocePopolaDB> ottieniVoce() throws PopolaDBHaTroppeVociException {
        List<VocePopolaDB> voci = this.popolaDBRepository.findAll();
    
        // se la tabella ha più di una voce, non si può
        // determinare se ri-popolare il DB
        if(voci.size() > 1) {
            throw new PopolaDBHaTroppeVociException("Non si può determinare se ri-popolare il DB. "
                                                +"Trovato " + voci.size() + " voci in DB, se ne aspettava una.");
        }

        // se non c'è la voce
        if (voci.isEmpty()) {
            return Optional.empty();
        }
        
        // è rimasto esattamente la voce
        return Optional.ofNullable(voci.getFirst());
    }

    /**
     * Devo caricare comuni e province?
     */
    public boolean devoPopolareDBComuniEProvince() {
        Optional<VocePopolaDB> forseVoce = this.ottieniVoce();
        boolean voceNonEsiste = forseVoce.isEmpty();
        // se la voce non esiste in DB, sicuramente
        // bisogna caricare comuni e province, indipendentemente
        // che esiste il campo specifico "caricato comuni ecc." 
        if(voceNonEsiste) {
            return true;
        }
        // la voce esiste
        VocePopolaDB voce = forseVoce.get();
        // se la voce esiste, vediamo cosa c'è nel campo che ci interessa
        // devo popolare il DB solo se il DB ci dice che
        // non ha caricato comuni e province
        boolean devoPopolareDB = !voce.getCaricatoComuniEProvince();
        
        return devoPopolareDB;
    }
    
}
