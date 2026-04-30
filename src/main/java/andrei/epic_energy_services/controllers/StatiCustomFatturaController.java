package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.entities.StatoCustomFattura;
import andrei.epic_energy_services.payloads.in_request.StatoCustomFatturaDTO;
import andrei.epic_energy_services.services.StatiCustomFatturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stati-custom-fatture")
@RequiredArgsConstructor
public class StatiCustomFatturaController {

    private final StatiCustomFatturaService statiCustomFatturaService;

    @GetMapping
    public List<StatoCustomFattura> getAll() {
        return statiCustomFatturaService.getAll();
    }

    @GetMapping("/{idStato}")
    public StatoCustomFattura getById(@PathVariable UUID idStato) {
        return statiCustomFatturaService.getById(idStato);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public StatoCustomFattura create(@RequestBody @Valid StatoCustomFatturaDTO body) {
        return statiCustomFatturaService.create(body.statoCustom());
    }

    @PutMapping("/{idStato}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public StatoCustomFattura update(
            @PathVariable UUID idStato,
            @RequestBody @Valid StatoCustomFatturaDTO body
    ) {
        return statiCustomFatturaService.update(idStato, body.statoCustom());
    }

    @DeleteMapping("/{idStato}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void delete(@PathVariable UUID idStato) {
        statiCustomFatturaService.delete(idStato);
    }
}