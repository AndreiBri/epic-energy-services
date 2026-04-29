package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientiRepository extends JpaRepository<Cliente, UUID>, JpaSpecificationExecutor<Cliente> {

    // query con filtri opzionali
    // se il parametro e null il filtro viene ignorato
//    @Query("SELECT c FROM Cliente c WHERE " +
//            "(:fatturatoAnnuale IS NULL OR c.fatturatoAnnuale = :fatturatoAnnuale) AND " +
//            "(:dataInserimento IS NULL OR c.dataInserimento = :dataInserimento) AND " +
//            "(:dataUltimoContatto IS NULL OR c.dataUltimoContatto = :dataUltimoContatto) AND " +
//            "(:ragioneSociale IS NULL OR c.ragioneSociale LIKE %:ragioneSociale%)")
//    Page<Cliente> findAllWithFilters(
//            @Param("fatturatoAnnuale") BigDecimal fatturatoAnnuale,
//            @Param("dataInserimento") LocalDate dataInserimento,
//            @Param("dataUltimoContatto") LocalDate dataUltimoContatto,
//            @Param("ragioneSociale") String ragioneSociale,
//            Pageable pageable
//    );

}
