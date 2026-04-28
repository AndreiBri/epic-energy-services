package andrei.epic_energy_services.repositories;

import andrei.epic_energy_services.entities.Cliente;
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
public interface ClientiRepository extends JpaRepository<Cliente, UUID> {

    // query con filtri opzionali
    // se il parametro e null il filtro viene ignorato
    @Query("SELECT c FROM Cliente c WHERE " +
            "(:fatturatoAnnuale IS NULL OR c.fatturatoAnnuale = :fatturatoAnnuale) AND " +
            "(:dataInserimento IS NULL OR c.dataInserimento = :dataInserimento) AND " +
            "(:dataUltimoContatto IS NULL OR c.dataUltimoContatto = :dataUltimoContatto) AND " +
            "(:nome IS NULL OR c.ragioneSociale LIKE %:nome%)")
    Page<Cliente> findAllWithFilters(
            @Param("fatturatoAnnuale") BigDecimal fatturatoAnnuale,
            @Param("dataInserimento") LocalDate dataInserimento,
            @Param("dataUltimoContatto") LocalDate dataUltimoContatto,
            @Param("nome") String nome,
            Pageable pageable
    );
}
