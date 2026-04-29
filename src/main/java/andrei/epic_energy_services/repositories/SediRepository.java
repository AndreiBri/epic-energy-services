package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.Sede;
import andrei.epic_energy_services.enums.TipoSede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SediRepository extends JpaRepository<Sede, UUID> {
    List<Sede> findByClienteIdCliente(UUID idCliente);
    Optional<Sede> findByClienteIdClienteAndTipoSede(UUID idCliente, TipoSede tipoSede);
    boolean existsByClienteIdClienteAndTipoSede(UUID idCliente, TipoSede tipoSede);
}
