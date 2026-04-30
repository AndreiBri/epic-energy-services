package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.entities.AssociazioneStatoCustomFattura;
import andrei.epic_energy_services.payloads.in_request.AssociazioneStatoCustomFatturaMandatoDTO;
import andrei.epic_energy_services.payloads.in_response.AssociazioneStatoCustomFatturaDaMandareDTO;
import andrei.epic_energy_services.services.AssociazioniStatiCustomFattureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    //   mi serve un file per ottenere la richiesta del cliente , dobbiamo unire la fattura con il ruolo custom scelto
//    poi mandare la risposta con la fattura e il ruolo custom scelto
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssociazioneStatoCustomFatturaDaMandareDTO associaStatoCustomFattura(@RequestBody AssociazioneStatoCustomFatturaMandatoDTO body) {
//        una volta ottenuta  richiesta cliente devo salvare e ritornare la fattura con lo stato richiesto

        AssociazioneStatoCustomFattura ass = this.associazioniStatiCustomFattureService.save(body);


        return new AssociazioneStatoCustomFatturaDaMandareDTO(ass);
    }

}
