package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.Cliente;
import andrei.epic_energy_services.entities.Comune;
import andrei.epic_energy_services.entities.Sede;
import andrei.epic_energy_services.enums.TipoSede;
import andrei.epic_energy_services.exceptions.InvalidDataException;
import andrei.epic_energy_services.exceptions.NotFoundException;
import andrei.epic_energy_services.repositories.ClientiRepository;
import andrei.epic_energy_services.repositories.ComuniRepository;
import andrei.epic_energy_services.repositories.SediRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SediService {

    private final SediRepository sediRepository;
    private final ClientiRepository clientiRepository;
    private final ComuniRepository comuniRepository;

    public List<Sede> getSediByCliente(UUID idCliente) {
        return sediRepository.findByClienteIdCliente(idCliente);
    }

    public Sede getSedeById(UUID idSede) {
        return sediRepository.findById(idSede)
                .orElseThrow(() -> new NotFoundException("Sede con id " + idSede + " non trovata"));
    }

    public Sede createSede(UUID idCliente, UUID idComune, TipoSede tipoSede,
                           String via, String civico, String localita, String cap) {

        // un cliente può avere al massimo una sede per tipo
        if (sediRepository.existsByClienteIdClienteAndTipoSede(idCliente, tipoSede)) {
            throw new InvalidDataException(
                    "Il cliente ha già una sede di tipo " + tipoSede
            );
        }

        Cliente cliente = (Cliente) clientiRepository.findById(idCliente)
                .orElseThrow(() -> new NotFoundException("Cliente con id " + idCliente + " non trovato"));

        Comune comune = comuniRepository.findById(idComune)
                .orElseThrow(() -> new NotFoundException("Comune con id " + idComune + " non trovato"));

        Sede sede = Sede.builder()
                .cliente(cliente)
                .comune(comune)
                .tipoSede(tipoSede)
                .via(via)
                .civico(civico)
                .localita(localita)
                .cap(cap)
                .build();

        return sediRepository.save(sede);
    }

    public Sede updateSede(UUID idSede, UUID idComune, String via,
                           String civico, String localita, String cap) {

        Sede sede = getSedeById(idSede);

        Comune comune = comuniRepository.findById(idComune)
                .orElseThrow(() -> new NotFoundException("Comune con id " + idComune + " non trovato"));

        sede.setComune(comune);
        sede.setVia(via);
        sede.setCivico(civico);
        sede.setLocalita(localita);
        sede.setCap(cap);

        return sediRepository.save(sede);
    }

    public void deleteSede(UUID idSede) {
        Sede sede = getSedeById(idSede);
        sediRepository.delete(sede);
    }
}