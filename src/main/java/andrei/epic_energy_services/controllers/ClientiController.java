package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.payloads.in_request.AggiornaClienteDTO;
import andrei.epic_energy_services.payloads.in_request.NuovoClienteMandatoDTO;
import andrei.epic_energy_services.payloads.in_response.ClienteDaMandareDTO;
import andrei.epic_energy_services.services.ClientiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClientiController {

    @Autowired
    private ClientiService clientiService;

    // GET /clienti - lista paginata con filtri e ordinamento opzionali
    // accessibile da USER e ADMIN
    @GetMapping
    public Page<ClienteDaMandareDTO> getAllClienti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            // filtri opzionali
            @RequestParam(required = false) BigDecimal fatturatoAnnuale,
            @RequestParam(required = false) LocalDate dataInserimento,
            @RequestParam(required = false) LocalDate dataUltimoContatto,
            @RequestParam(required = false) String ragioneSociale,
            @RequestParam(required = false) String nomeProvincia,
            // ordinamento - default per ragione sociale
            @RequestParam(defaultValue = "ragioneSociale") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return clientiService.getAllClienti(
                page, size,
                fatturatoAnnuale,
                dataInserimento,
                dataUltimoContatto,
                ragioneSociale,
                sortBy, nomeProvincia, sortDir);
    }

    // GET /clienti/:clienteId - singolo cliente
    @GetMapping("/{clienteId}")
    public ClienteDaMandareDTO getById(@PathVariable UUID clienteId) {
        return clientiService.getById(clienteId);
    }

    // POST /clienti - crea nuovo cliente
    // accessibile da USER e ADMIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDaMandareDTO create(@RequestBody @Valid NuovoClienteMandatoDTO dto) {
        return clientiService.save(dto);
    }

    // PATCH /clienti/:clienteId - aggiorna cliente
    // solo ADMIN
    @PatchMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ClienteDaMandareDTO update(@PathVariable UUID clienteId,
                                      @RequestBody AggiornaClienteDTO dto) {
        return clientiService.update(clienteId, dto);
    }

    // PATCH /clienti/:clienteId/logo - carica logo su cloudinary
    // solo ADMIN
    @PatchMapping("/{clienteId}/logo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ClienteDaMandareDTO uploadLogo(@PathVariable UUID clienteId,
                                          @RequestParam("logo") MultipartFile file) throws IOException {
        return clientiService.uploadLogo(clienteId, file);
    }

    // DELETE /clienti/:clienteId - elimina cliente
    // solo ADMIN
    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable UUID clienteId) {
        clientiService.delete(clienteId);
    }
}
