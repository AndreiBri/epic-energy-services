package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.entities.Sede;
import andrei.epic_energy_services.enums.TipoSede;
import andrei.epic_energy_services.services.SediService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sedi")
@RequiredArgsConstructor
public class SediController {

    private final SediService sediService;

    @GetMapping("/cliente/{idCliente}")
    public List<Sede> getSediByCliente(@PathVariable UUID idCliente) {
        return sediService.getSediByCliente(idCliente);
    }

    @GetMapping("/{idSede}")
    public Sede getSedeById(@PathVariable UUID idSede) {
        return sediService.getSedeById(idSede);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Sede createSede(
            @RequestParam UUID idCliente,
            @RequestParam UUID idComune,
            @RequestParam TipoSede tipoSede,
            @RequestParam String via,
            @RequestParam String civico,
            @RequestParam(required = false) String localita,
            @RequestParam String cap
    ) {
        return sediService.createSede(idCliente, idComune, tipoSede, via, civico, localita, cap);
    }

    @PutMapping("/{idSede}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Sede updateSede(
            @PathVariable UUID idSede,
            @RequestParam UUID idComune,
            @RequestParam String via,
            @RequestParam String civico,
            @RequestParam(required = false) String localita,
            @RequestParam String cap
    ) {
        return sediService.updateSede(idSede, idComune, via, civico, localita, cap);
    }

    @DeleteMapping("/{idSede}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteSede(@PathVariable UUID idSede) {
        sediService.deleteSede(idSede);
    }
}