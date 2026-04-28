package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.VocePopolaDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopolaDBRepository extends JpaRepository<VocePopolaDB, Long> {
    
    @Query("SELECT v FROM VocePopolaDB v")
    List<VocePopolaDB> trovaTutteVoci();
    
}
