package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.StatoCustomFattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StatoCustomFatturaRepository extends JpaRepository<StatoCustomFattura, UUID> {
    Optional<StatoCustomFattura> findByStatoCustom(String statoCustom);
    boolean existsByStatoCustom(String statoCustom);
}