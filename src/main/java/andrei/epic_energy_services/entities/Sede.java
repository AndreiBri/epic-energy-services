package andrei.epic_energy_services.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "sedi")
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
