package andrei.epic_energy_services.entities;

import andrei.epic_energy_services.exceptions.InvalidDataFormatException;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "province")
public class Provincia {
    
    @Id
    @GeneratedValue
    @Column(name = "id_provincia")
    private UUID idProvincia;
    
    @Column(name = "sigla_provincia", nullable = false, unique = true)
    private String siglaProvincia;

    @Column(name = "nome_provincia", nullable = false, unique = true)
    private String nomeProvincia;

    @Column(name = "regione", nullable = false)
    private String regione;
    
    protected Provincia() {}
    
    public Provincia(String siglaProvincia, String nomeProvincia, String regione) throws InvalidDataFormatException {
        // se la sigla della provincia ha un numero di caratteri diverso da 2
        if (siglaProvincia.length() != 2) {
            throw new InvalidDataFormatException("La sigla di una provincia deve avere esattamente 2 caratteri. "
                                                +"Ricevuto invece " + siglaProvincia.length() + "caratteri.");
        }
        this.siglaProvincia = siglaProvincia;
        this.nomeProvincia = nomeProvincia;
        this.regione = regione;
    }

    public UUID getIdProvincia() {
        return idProvincia;
    }

    public String getNomeProvincia() {
        return nomeProvincia;
    }

    public String getRegione() {
        return regione;
    }

    public String getSiglaProvincia() {
        return siglaProvincia;
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "idProvincia=" + idProvincia +
                ", siglaProvincia='" + siglaProvincia + '\'' +
                ", nomeProvincia='" + nomeProvincia + '\'' +
                ", regione='" + regione + '\'' +
                '}';
    }
}
