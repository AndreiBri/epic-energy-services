package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.Fattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface FattureRepository extends JpaRepository<Fattura, UUID> {

    @Query("""
        SELECT f FROM Fattura f
        JOIN f.associazioniStati a
        JOIN a.statoCustomFattura s
        WHERE (:idCliente IS NULL OR f.cliente.idCliente = :idCliente)
          AND (:statoCustom IS NULL OR s.statoCustom = :statoCustom)
          AND (:data IS NULL OR f.dataCreazione = :data)
          AND (:anno IS NULL OR YEAR(f.dataCreazione) = :anno)
          AND (:importoMin IS NULL OR f.importo >= :importoMin)
          AND (:importoMax IS NULL OR f.importo <= :importoMax)
    """)
    Page<Fattura> findWithFilters(
            @Param("idCliente") UUID idCliente,
            @Param("statoCustom") String statoCustom,
            @Param("data") LocalDate data,
            @Param("anno") Integer anno,
            @Param("importoMin") BigDecimal importoMin,
            @Param("importoMax") BigDecimal importoMax,
            Pageable pageable
    );
}