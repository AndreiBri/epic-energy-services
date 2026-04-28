package andrei.epic_energy_services.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "comuni")
public class Comune {
    
    @Id
    @GeneratedValue
    @Column(name = "id_comune")
    private UUID idComune;
    
    @ManyToOne
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia provincia;
    
    @Column(name = "nome_comune", nullable = false)
    private String nomeComune;
    
    protected Comune() {}
    
    public Comune(Provincia provincia, String nomeComune) {
        this.provincia = provincia;
        this.nomeComune = nomeComune;
    }

    public UUID getIdComune() {
        return idComune;
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    @Override
    public String toString() {
        return "Comune{" +
                "idComune=" + idComune +
                ", provincia=" + provincia +
                ", nomeComune='" + nomeComune + '\'' +
                '}';
    }
}
