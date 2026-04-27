package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.VocePopolaDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopolaDBRepository extends JpaRepository<VocePopolaDB, Long> {
    
}
