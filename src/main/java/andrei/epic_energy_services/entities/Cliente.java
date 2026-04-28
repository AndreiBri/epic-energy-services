package andrei.epic_energy_services.entities;

import andrei.epic_energy_services.enums.FormaGiuridica;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCliente;

    private String ragioneSociale;
    private String partitaIva;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String pec;

    private String telefono;
    private BigDecimal fatturatoAnnuale;

    @Enumerated(EnumType.STRING)
    private FormaGiuridica formaGiuridica;

    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;

    // data impostata automaticamente alla creazione
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;

    @Column(columnDefinition = "text")
    private String logoAziendaleUrl;

    // aggiungere relazione con Sede quando i colleghi implementano l'entita
    // @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    // private List<Sede> sedi = new ArrayList<>();

    // ---------- equals e hashCode ----------
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Cliente cliente = (Cliente) o;
        return getIdCliente() != null && Objects.equals(getIdCliente(), cliente.getIdCliente());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
