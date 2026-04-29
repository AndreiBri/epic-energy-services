package andrei.epic_energy_services.specification;

import andrei.epic_energy_services.entities.Cliente;
import andrei.epic_energy_services.entities.Comune;
import andrei.epic_energy_services.entities.Provincia;
import andrei.epic_energy_services.entities.Sede;
import andrei.epic_energy_services.enums.TipoSede;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClientiSpecification {

    public static Specification<Cliente> hasFatturatoAnnuale(BigDecimal fatturato) {
        return (root, query, cb) -> fatturato == null ? null :
                cb.equal(root.get("fatturatoAnnuale"), fatturato);
    }

    public static Specification<Cliente> hasDataInserimento(LocalDate dataInserimento) {
        return (root, query, cb) -> dataInserimento == null ? null :
                cb.equal(root.get("dataInserimento"), dataInserimento);

    }

    public static Specification<Cliente> hasDataUltimoContatto(LocalDate dataUltimoContatto) {
        return (root, query, cb) -> dataUltimoContatto == null ? null :
                cb.equal(root.get("dataUltimoContatto"), dataUltimoContatto);
    }


    public static Specification<Cliente> hasragioneSociale(String ragioneSociale) {
        return (root, query, cb) -> ragioneSociale == null ? null :
                cb.like(cb.lower(root.get("ragioneSociale")), "%" + ragioneSociale.toLowerCase() + "%");
    }

    public static Specification<Cliente> hasProvinciaSedeLegale(String nomeProvincia) {
        return (root, query, cb) -> {
            if (nomeProvincia == null) return null;
            Join<Cliente, Sede> sedi = root.join("sedi", JoinType.LEFT);
            Join<Sede, Comune> comune = sedi.join("comune", JoinType.LEFT);
            Join<Comune, Provincia> provincia = comune.join("provincia", JoinType.LEFT);
            return cb.and(
                    cb.equal(sedi.get("tipoSede"), TipoSede.SEDE_LEGALE),
                    cb.like(provincia.get("nomeProvincia"), "%" + nomeProvincia + "%")
            );
        };
    }
}
