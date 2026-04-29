package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.Comune;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComuniRepository extends JpaRepository<Comune, UUID> {


    /**
     * Trova comuni il cui nome corrisponde a.
     */
    @Query("SELECT c FROM Comune c WHERE LOWER(c.nomeComune) LIKE :matchNomeComune")
    Page<Comune> trovaComuniConMatch(String matchNomeComune, Pageable pageable);
    
}
