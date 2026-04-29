package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.entities.Comune;
import andrei.epic_energy_services.entities.Utente;
import andrei.epic_energy_services.exceptions.InvalidDataException;
import andrei.epic_energy_services.exceptions.InvalidDataFormatException;
import andrei.epic_energy_services.payloads.in_response.ComuneDaMandareDTO;
import andrei.epic_energy_services.services.ComuniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comuni")
public class ComuniController {
    
    @Autowired
    private ComuniService comuniService;

    /**
     * Trova comuni con questo match al nome del comune.
     */
    @GetMapping
    public Page<ComuneDaMandareDTO> findOwnArticles(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(defaultValue = "") String nomeComune) throws InvalidDataException
    {

        
        // non è stato fornito il query param
        if(nomeComune == null) {
            throw new InvalidDataException("Manca il query param 'nomeComune'.");
        }
        
        // il query param è vuoto
        if(nomeComune.trim().isEmpty()) {
            throw new InvalidDataException("Il query param 'nomeComune' è vuoto. Inserisci qualcosa.");
        }
        
        // filtra i comuni
        Page<Comune> comuniPaginati = this.comuniService.trovaComuniConMatch(nomeComune, page, size);
        return comuniPaginati.map(comune -> new ComuneDaMandareDTO(comune));
    }

}
