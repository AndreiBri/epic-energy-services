package andrei.epic_energy_services.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "popola_db")
public class VocePopolaDB {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "caricato_comuni_e_province", nullable = false)
    private boolean caricatoComuniEProvince;

    
    public VocePopolaDB() {}
    
    
    public boolean isCaricatoComuniEProvince() {
        return caricatoComuniEProvince;
    }

    public void setCaricatoComuniEProvince(boolean caricatoComuniEProvince) {
        this.caricatoComuniEProvince = caricatoComuniEProvince;
    }
}
