package andrei.epic_energy_services.runners;

import andrei.epic_energy_services.services.PopolaDBComuniEProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Popola il DB con comuni e province.
 */
@Component
public class PopolaDB implements CommandLineRunner {
    
    @Autowired
    private PopolaDBComuniEProvinceService popolaDBComuniEProvinceService;
    
    @Override
    public void run(String... args) throws Exception {
        
        // la path dalla directory /resources
        String pathCsvComuni = "popola_db/comuni-italiani.csv";
        String pathCsvProvince = "popola_db/province-italianes.csv";
        
        this.popolaDBComuniEProvinceService.popolaDBComuniEProvinceSeDevo(pathCsvComuni, pathCsvProvince);
        
        
    }
    
    
}
