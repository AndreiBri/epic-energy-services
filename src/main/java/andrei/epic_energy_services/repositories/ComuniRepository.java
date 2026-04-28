package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComuniRepository extends JpaRepository<Comune, UUID> {
    
    
    
}
