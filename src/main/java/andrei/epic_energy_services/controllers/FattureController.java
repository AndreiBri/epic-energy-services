package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.entities.Fattura;
import andrei.epic_energy_services.payloads.in_request.FatturaDTO;
import andrei.epic_energy_services.services.FattureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/fatture")
@RequiredArgsConstructor
public class FattureController {

    private final FattureService fattureService;

    @GetMapping
    public Page<Fattura> getAllFatture(
            @RequestParam(required = false) UUID idCliente,
            @RequestParam(required = false) String statoCustom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam(required = false) Integer anno,
            @RequestParam(required = false) BigDecimal importoMin,
            @RequestParam(required = false) BigDecimal importoMax,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dataCreazione") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fattureService.getAllFatture(idCliente, statoCustom, data, anno, importoMin, importoMax, pageable);
    }

    @GetMapping("/{idFattura}")
    public Fattura getFatturaById(@PathVariable UUID idFattura) {
        return fattureService.getFatturaById(idFattura);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Fattura createFattura(
            @RequestParam UUID idCliente,
            @RequestBody @Valid FatturaDTO body
    ) {
        return fattureService.createFattura(
                idCliente,
                body.dataCreazione(),
                body.importo(),
                body.numero(),
                body.statoCustom()
        );
    }

    @PutMapping("/{idFattura}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Fattura updateFattura(
            @PathVariable UUID idFattura,
            @RequestBody @Valid FatturaDTO body
    ) {
        return fattureService.updateFattura(
                idFattura,
                body.dataCreazione(),
                body.importo(),
                body.numero(),
                body.statoCustom()
        );
    }

    @DeleteMapping("/{idFattura}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteFattura(@PathVariable UUID idFattura) {
        fattureService.deleteFattura(idFattura);
    }
}