package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.AssociazioneStatoCustomFattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssociazioneStatoCustomFatturaRepository extends JpaRepository<AssociazioneStatoCustomFattura, UUID> {
    Optional<AssociazioneStatoCustomFattura> findByFatturaIdFattura(UUID idFattura);
}
