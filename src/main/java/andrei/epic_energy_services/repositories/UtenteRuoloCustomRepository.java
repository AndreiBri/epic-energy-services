package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.UtenteRuoloCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus
public interface UtenteRuoloCustomRepository extends JpaRepository<UtenteRuoloCustom, UUID> {
}
