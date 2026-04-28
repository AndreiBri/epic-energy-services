package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.VocePopolaDB;
import andrei.epic_energy_services.exceptions.PopolaDBException;
import andrei.epic_energy_services.repositories.PopolaDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PopolaDBService {
    
    @Autowired
    private PopolaDBRepository popolaDBRepository;

    @Autowired
    private static final Logger LOGGER = Logger.getLogger(PopolaDBService.class.getName());


    /**
     * Metodo principale per popolare il DB.
     * Puoi invocare solo questo per eseguire l'intera funzionalità
     * di popolamento del DB.
     */
    public void popolaDBComuniEProvinceSeDevo(String pathCsvComuni, String pathCsvProvince) throws PopolaDBException, IOException 
    {
            
        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: sto verificando se devo popolare DB");
        
        boolean devoPopolareDB = this.devoPopolareDBComuniEProvince();

        // se non devo popolare il DB, mi fermo
        if(!devoPopolareDB) {
            LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: non devo popolare DB");
            return;
        }
        
        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: devo popolare DB: inizio caricamento dati in DB...");
        
        this.popolaDBComuniEProvince(pathCsvComuni, pathCsvProvince);

        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: devo popolare DB: fine caricamento dati in DB");
        
        // ho popolato il DB, quindi imposto la voce rilevante       
        this.impostaVocePopolaDBComuniEProvinceComeCaricato();
        
        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: devo popolare DB: operazione completata con successo");
        
    }
    
    
    private void popolaDBComuniEProvince(String pathCsvComuni, String pathCsvProvince) 
    {
        // carica citta e province in DB
        // usa libreria per leggere il CSV?

        // ClassPathResource resource = new ClassPathResource(pathCsvComuni);
        //
        // InputStream inputStream = resource.getInputStream();
        //
        // System.out.println(inputStream);
        
    }
    

    /**
     * Ottieni tutte le voci della tabella popola_db.
     * Ci devono essere <= 1 voci.
     * 
     */
    public Optional<VocePopolaDB> ottieniVoce() throws PopolaDBException {
        List<VocePopolaDB> voci = this.popolaDBRepository.findAll();
    
        // se la tabella ha più di una voce, non si può
        // determinare se ri-popolare il DB
        if(voci.size() > 1) {
            throw new PopolaDBException("Non si può determinare se ri-popolare il DB. "
                                                +"Trovato " + voci.size() + " voci in DB, se ne aspettava una.");
        }

        // se non c'è la voce
        if (voci.isEmpty()) {
            return Optional.empty();
        }
        
        // è rimasto esattamente la voce
        return Optional.ofNullable(voci.getFirst());
    }

    /**
     * Devo caricare comuni e province?
     */
    public boolean devoPopolareDBComuniEProvince() {
        Optional<VocePopolaDB> forseVoce = this.ottieniVoce();
        boolean voceNonEsiste = forseVoce.isEmpty();
        // se la voce non esiste in DB, sicuramente
        // bisogna caricare comuni e province, indipendentemente
        // che esiste il campo specifico "caricato comuni ecc." 
        if(voceNonEsiste) {
            return true;
        }
        // la voce esiste
        VocePopolaDB voce = forseVoce.get();
        // se la voce esiste, vediamo cosa c'è nel campo che ci interessa
        // devo popolare il DB solo se il DB ci dice che
        // non ha caricato comuni e province

        return !voce.isCaricatoComuniEProvince();
    }

    
    /**
     * Aggiorna il campo che si occupa di tracciare 
     * se comuni e province sono state caricate.
     */
    public void impostaVocePopolaDBComuniEProvinceComeCaricato() {
        List<VocePopolaDB> voci = this.popolaDBRepository.trovaTutteVoci();
        
        // se non c'è nessuna voce
        if(voci.isEmpty()) {
            // crea la voce e setta il campo rilevante come true
            
            VocePopolaDB nuovaVoce = new VocePopolaDB();
            nuovaVoce.setCaricatoComuniEProvince(true);
            this.popolaDBRepository.save(nuovaVoce);
            
            return;
        }
        
        // ci sono più voci: errore
        if(voci.size() > 1) {
            throw new PopolaDBException("Ci sono più voci, se ne aspettava una.");
        }
        
        // c'è una sola voce, aggiornala
        voci.getFirst().setCaricatoComuniEProvince(true);
        this.popolaDBRepository.save(voci.getFirst());
    }
    
}
