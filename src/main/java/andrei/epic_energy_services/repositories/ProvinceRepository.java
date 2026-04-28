package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProvinceRepository extends JpaRepository<Provincia, UUID> {
    
    // ottieni la provincia il cui nome corrisponde 
    // esattamente a quello in input
    @Query("SELECT p FROM Provincia p WHERE p.nomeProvincia = :nomeProvincia")
    Optional<Provincia> trovaProvinciaPerNomeEsatto(String nomeProvincia);
    
}
