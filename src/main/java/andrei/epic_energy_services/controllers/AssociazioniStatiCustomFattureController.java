package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.entities.AssociazioneStatoCustomFattura;
import andrei.epic_energy_services.payloads.in_response.AssociazioneStatoCustomFatturaDaMandareDTO;
import andrei.epic_energy_services.services.AssociazioniStatiCustomFattureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/associazioni-stati-custom-fatture")
public class AssociazioniStatiCustomFattureController {


    @Autowired
    private AssociazioniStatiCustomFattureService associazioniStatiCustomFattureService;

    @GetMapping
    public List<AssociazioneStatoCustomFatturaDaMandareDTO> getAll() {

        List<AssociazioneStatoCustomFattura> lista = associazioniStatiCustomFattureService.findAll();


        return lista.stream().map(associazione -> new AssociazioneStatoCustomFatturaDaMandareDTO(associazione)).toList();
    }
}
