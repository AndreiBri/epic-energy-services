package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.entities.Fattura;
import andrei.epic_energy_services.payloads.in_request.NuovaFatturaMandataDTO;
import andrei.epic_energy_services.services.AppEmailService;
import andrei.epic_energy_services.services.FattureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    
    @Autowired
    private AppEmailService appEmailService;

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
            @RequestBody @Valid NuovaFatturaMandataDTO body
    ) {
        Fattura fattura = fattureService.createFattura(
                body.idCliente(),
                body.dataCreazione(),
                body.importo(),
                body.numero()
        );
        
        // fattura.getCliente()
        this.appEmailService.mandaEmailFatturaACliente(fattura.getCliente());
        // una volta creata con successo la fattura, 
        // mandiamo l'email al cliente
        
        return fattura;
    }

//    @PutMapping("/{idFattura}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    public Fattura updateFattura(
//            @PathVariable UUID idFattura,
//            @RequestBody @Valid NuovaFatturaMandataDTO body
//    ) {
//        return fattureService.updateFattura(
//                idFattura,
//                body.dataCreazione(),
//                body.importo(),
//                body.numero()
//        );
//    }

    @DeleteMapping("/{idFattura}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteFattura(@PathVariable UUID idFattura) {
        fattureService.deleteFattura(idFattura);
    }
}