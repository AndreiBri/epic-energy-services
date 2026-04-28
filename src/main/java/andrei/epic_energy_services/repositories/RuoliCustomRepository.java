package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.RuoloCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RuoliCustomRepository extends JpaRepository<RuoloCustom, UUID> {

    Optional<RuoloCustom> findByNome(String nome);

    boolean existsByNome(String nome);
}
