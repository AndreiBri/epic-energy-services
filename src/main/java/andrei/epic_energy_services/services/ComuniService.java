package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.Comune;
import andrei.epic_energy_services.repositories.ComuniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ComuniService {
    
    @Autowired
    private ComuniRepository comuniRepository;

    /**
     * Trova i comuni con match del nome comune.
     */
    public Page<Comune> trovaComuniConMatch(String nomeComuneParziale, int page, int size) {
        // / the size of each page (how many elements in each page)
        int finalSize = Math.min(10, size);
        // the page number
        int finalPage = Math.max(0, page);
        // page is the function that will get translated to SQL,
        // that will in turn filter the result set
        Pageable pageable = PageRequest.of(finalPage, finalSize, Sort.by("nomeComune"));
        String matchNomeComune = "%" + nomeComuneParziale.toLowerCase() + "%";

        System.out.println(matchNomeComune);
        return this.comuniRepository.trovaComuniConMatch(matchNomeComune, pageable);
    }
    
}
