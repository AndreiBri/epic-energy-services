package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.VocePopolaDB;
import andrei.epic_energy_services.exceptions.PopolaDBHaTroppeVociException;
import andrei.epic_energy_services.repositories.PopolaDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopolaDBService {
    
    @Autowired
    private PopolaDBRepository popolaDBRepository;

    /**
     * Ottieni tutte le voci della tabella popola_db.
     * Ci devono essere <= 1 voci.
     * 
     */
    public void ottieni() throws PopolaDBHaTroppeVociException {
        List<VocePopolaDB> voci = this.popolaDBRepository.findAll();

        System.out.println(voci);
    }
    
}
