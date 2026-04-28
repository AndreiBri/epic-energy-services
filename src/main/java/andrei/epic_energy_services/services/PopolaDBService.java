package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.VocePopolaDB;
import andrei.epic_energy_services.exceptions.PopolaDBException;
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
    protected Optional<VocePopolaDB> ottieniVoce() throws PopolaDBException {
        List<VocePopolaDB> voci = this.popolaDBRepository.findAll();

        // se la tabella ha più di una voce, non si può
        // determinare se ri-popolare il DB
        if(voci.size() > 1) {
            throw new PopolaDBException("Non si può determinare se ri-popolare il DB. "
                    +"Trovato " + voci.size() + " voci in DB, se ne aspettava una.");
        }

        // se non c'è la voce
        if (voci.isEmpty()) {
            return Optional.empty();
        }

        // è rimasto esattamente la voce
        return Optional.ofNullable(voci.getFirst());
    }


}
