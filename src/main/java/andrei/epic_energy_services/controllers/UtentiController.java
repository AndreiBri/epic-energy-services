package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.payloads.in_response.UtenteResponseDTO;
import andrei.epic_energy_services.services.UtentiService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtentiController {

    private final UtentiService utentiService;

    public UtentiController(UtentiService utentiService) {
        this.utentiService = utentiService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<UtenteResponseDTO> findAll() {
        return utentiService.findAll().stream().map(utente -> {
            return new UtenteResponseDTO(utente);
        }).toList();
    }

//    @GetMapping("/{id}")
//    public UtenteResponseDTO findById(@PathVariable UUID id) {
//        Utente utente = this.utentiService.findById(id);
//        return new UtenteResponseDTO(utente);
//    }


}
