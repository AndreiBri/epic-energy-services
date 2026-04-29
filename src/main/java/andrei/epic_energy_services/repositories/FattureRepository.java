package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FattureRepository extends JpaRepository<Fattura, UUID>, JpaSpecificationExecutor<Fattura> {
}