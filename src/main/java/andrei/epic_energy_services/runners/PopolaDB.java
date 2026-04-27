package andrei.epic_energy_services.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Popola il DB con comuni e province.
 */
@Component
public class PopolaDB implements CommandLineRunner {
    
    
    
    @Override
    public void run(String... args) throws Exception {
        // verifica se, nella tabella popola_db,
        // il campo caricato_comuni ha valore true
        // il campoe caricato_province ha valore true
        // System.out.println("runner works");
        
        // se non esiste nessuna riga, popola il DB con comuni e province, 
        // poi aggiungi la riga che con "caricato comuni e province" = true
        
        // se esiste esattamente una riga, vedi quale valore
        // il campo "caricato comuni e province ha"
        
        // se ha il campo falso
        
        // se esiste più di una riga, lancia errore
        // dicendo che non si  può determinare se si deve popolare il DB o no 
        
        // carica citta e province in DB     
    }
    
    
}
