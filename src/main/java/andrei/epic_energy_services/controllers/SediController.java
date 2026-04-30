package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.entities.Sede;
import andrei.epic_energy_services.payloads.in_request.SedeDTO;
import andrei.epic_energy_services.services.SediService;
import jakarta.validation.Valid;
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
            @RequestBody @Valid SedeDTO body
    ) {
        return sediService.createSede(
                body.idCliente(),
                body.idComune(),
                body.tipoSede(),
                body.via(),
                body.civico(),
                body.localita(),
                body.cap()
        );
    }

    @PutMapping("/{idSede}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Sede updateSede(
            @PathVariable UUID idSede,
            @RequestBody @Valid SedeDTO body
    ) {
        return sediService.updateSede(
                idSede,
                body.idComune(),
                body.via(),
                body.civico(),
                body.localita(),
                body.cap()
        );
    }

    @DeleteMapping("/{idSede}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteSede(@PathVariable UUID idSede) {
        sediService.deleteSede(idSede);
    }
}