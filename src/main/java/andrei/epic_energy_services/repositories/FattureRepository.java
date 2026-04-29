package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.Fattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface FattureRepository extends JpaRepository<Fattura, UUID> {

    Page<Fattura> findByClienteIdCliente(UUID idCliente, Pageable pageable);

    Page<Fattura> findByStatoCustom(String statoCustom, Pageable pageable);

    @Query("""
        SELECT f FROM Fattura f
        WHERE (:idCliente IS NULL OR f.cliente.idCliente = :idCliente)
          AND (:stato IS NULL OR f.statoCustom = :stato)
          AND (:dataFrom IS NULL OR f.dataCreazione >= :dataFrom)
          AND (:dataTo IS NULL OR f.dataCreazione <= :dataTo)
          AND (:anno IS NULL OR YEAR(f.dataCreazione) = :anno)
          AND (:importoMin IS NULL OR f.importo >= :importoMin)
          AND (:importoMax IS NULL OR f.importo <= :importoMax)
    """)
    Page<Fattura> findWithFilters(
            @Param("idCliente") UUID idCliente,
            @Param("stato") String stato,
            @Param("dataFrom") LocalDate dataFrom,
            @Param("dataTo") LocalDate dataTo,
            @Param("anno") Integer anno,
            @Param("importoMin") Long importoMin,
            @Param("importoMax") Long importoMax,
            Pageable pageable
    );
}