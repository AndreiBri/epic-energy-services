package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.entities.RuoloCustom;
import andrei.epic_energy_services.payloads.in_request.RuoloCustomRequestDTO;
import andrei.epic_energy_services.payloads.in_response.RuoloCustomResponseDTO;
import andrei.epic_energy_services.services.RuoliCustomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ruoli-custom")
public class RuoliCustomController {

    private final RuoliCustomService ruoliCustomService;

    public RuoliCustomController(RuoliCustomService ruoliCustomService) {
        this.ruoliCustomService = ruoliCustomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RuoloCustomResponseDTO create(@RequestBody RuoloCustomRequestDTO dto) {

        RuoloCustom ruoloCustom = new RuoloCustom(dto.ruoloCustom());

        RuoloCustom ruoloCustomFromDb = this.ruoliCustomService.create(ruoloCustom);

//      Sto cercando di trasformare 'dto' in RuoloCustom a partire da RuoloCustomRequestDTO
//      input = RuoloCustomRequestDTO
//      output = RuoloCustom

        RuoloCustomResponseDTO ruoloCustomResponseDTO = new RuoloCustomResponseDTO(ruoloCustomFromDb);

        return ruoloCustomResponseDTO;
    }


    /**
     * Ottieni tutti i ruoli custom.
     * Non serve paginare perché si assume che
     * i ruoli custom siano "pochi".
     */
    @GetMapping
    public List<RuoloCustomResponseDTO> ottieniRuoliCustom() {
        
        List<RuoloCustom> listaRuoliCustom = this.ruoliCustomService.findAll();
        
        return listaRuoliCustom.stream().map(ruolo -> new RuoloCustomResponseDTO(ruolo)).toList();
    }
    
}
