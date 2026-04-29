package andrei.epic_energy_services.runners;

import andrei.epic_energy_services.entities.Utente;
import andrei.epic_energy_services.services.AppEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MandaEmailDemo implements CommandLineRunner {
    
    @Autowired
    private AppEmailService appEmailService;
    
    @Override
    public void run(String... args) throws Exception {
        
        // Utente utente = new Utente(
        //         "tave8",
        //         "giuseppetavella8@gmail.com",
        //         "1234",
        //         "Giuseppe",
        //         "Tavella"
        // );
        //
        // this.appEmailService.sendWelcome(utente);
        
    }
}
