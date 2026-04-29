package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.entities.RuoloCustom;
import andrei.epic_energy_services.payloads.in_request.RuoloCustomRequestDTO;
import andrei.epic_energy_services.payloads.in_response.RuoloCustomResponseDTO;
import andrei.epic_energy_services.services.RuoliCustomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ruoli-custom")
public class RuoliCustomController {

    private final RuoliCustomService ruoliCustomService;

    public RuoliCustomController(RuoliCustomService ruoliCustomService) {
        this.ruoliCustomService = ruoliCustomService;
    }

    @PostMapping
    public RuoloCustomResponseDTO create(@RequestBody RuoloCustomRequestDTO dto) {

        RuoloCustom ruoloCustom = new RuoloCustom(dto.ruoloCustom());

        RuoloCustom ruoloCustomFromDb = this.ruoliCustomService.create(ruoloCustom);

//      Sto cercando di trasformare 'dto' in RuoloCustom a partire da RuoloCustomRequestDTO
//      input = RuoloCustomRequestDTO
//      output = RuoloCustom

        RuoloCustomResponseDTO ruoloCustomResponseDTO = new RuoloCustomResponseDTO(ruoloCustomFromDb);

        return ruoloCustomResponseDTO;
    }

    
}
