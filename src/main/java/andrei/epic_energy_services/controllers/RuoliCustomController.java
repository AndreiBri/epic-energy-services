package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.entities.RuoloCustom;
import andrei.epic_energy_services.exceptions.InvalidUUIDStringException;
import andrei.epic_energy_services.helpers.StringHelper;
import andrei.epic_energy_services.payloads.in_request.RuoloCustomRequestDTO;
import andrei.epic_energy_services.payloads.in_response.RuoloCustomResponseDTO;
import andrei.epic_energy_services.services.RuoliCustomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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


    /**
     * Questo controller aggiunge un'associazione
     * tra un ruolo custom e un utente.
     * Esempio:
     *   
     *    vendita (ruolo custom) ASSOCIATA A Maria (utente)
     * 
     */
    @PostMapping("/{ruoloCustomId}/utenti/{utenteId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void aggiungiAssociazioneTraRuoloUtenteEUtente(@PathVariable String ruoloCustomId, 
                                                          @PathVariable String utenteId) throws InvalidUUIDStringException
    {
        // verifico che ruoloCustomId e utenteId 
        // siano dei validi UUID      
        // se uno dei due non è un valido UUID, rispondi con "richiesta malformata"     
        UUID ruoloCustomIdAsUUID = StringHelper.parseUUID(ruoloCustomId);
        UUID utenteIdAsUUID = StringHelper.parseUUID(utenteId);
        
        this.ruoliCustomService.aggiungiAssociazioneTraRuoloUtenteEUtente(ruoloCustomIdAsUUID, utenteIdAsUUID);
        
    }
    

}
