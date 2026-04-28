package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.Comune;
import andrei.epic_energy_services.entities.Provincia;
import andrei.epic_energy_services.entities.VocePopolaDB;
import andrei.epic_energy_services.exceptions.PopolaDBException;
import andrei.epic_energy_services.repositories.ComuniRepository;
import andrei.epic_energy_services.repositories.PopolaDBRepository;
import andrei.epic_energy_services.repositories.ProvinceRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

@Service
public class PopolaDBComuniEProvinceService extends PopolaDBService {
    
    @Autowired
    private PopolaDBRepository popolaDBRepository;
    
    @Autowired
    private ProvinceRepository provinceRepository;
    
    @Autowired
    private ComuniRepository comuniRepository;

    @Autowired
    private static final Logger LOGGER = Logger.getLogger(PopolaDBComuniEProvinceService.class.getName());


    /**
     * Metodo principale per popolare il DB.
     * Puoi invocare solo questo per eseguire l'intera funzionalità
     * di popolamento del DB.
     */
    public void popolaDBComuniEProvinceSeDevo(String pathCsvComuni, String pathCsvProvince) throws PopolaDBException, IOException 
    {
            
        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: sto verificando se devo popolare DB...");
        
        boolean devoPopolareDB = this.devoPopolareDBComuniEProvince();

        // se non devo popolare il DB, mi fermo
        if(!devoPopolareDB) {
            LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: non devo popolare DB");
            return;
        }
        
        
        // verifica la file path di province
        Path pathProvince = this.richiediFilePathValida(pathCsvProvince, "province");
        // verifica la file path di comuni
        Path pathComuni = this.richiediFilePathValida(pathCsvComuni, "comuni");
        
        
        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: devo popolare DB: inizio operazione di caricamento dati in DB");
        
        // prima rimuovi tutti i comuni
        this.comuniRepository.deleteAll();

        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: rimosso tutti i comuni in DB");
        
        // poi rimuovi tutte le province
        this.provinceRepository.deleteAll();

        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: rimosso tutte le province in DB");
        
        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: inizio caricamento dati in DB...");

        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: inizio caricamento province in DB...");
        
        this.popolaDBProvince(pathProvince);

        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: fine caricamento province in DB");

        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: inizio caricamento comuni in DB...");

        this.popolaDBComuni(pathComuni);

        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: fine caricamento comuni in DB");
        
        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: fine caricamento dati in DB");
        
        // ho popolato il DB, quindi imposto la voce rilevante       
        this.impostaVocePopolaDBComuniEProvinceComeCaricato();
        
        LOGGER.info("STARTUP TASK: POPOLA DB: comuni e province: operazione completata con successo");
        
    }
    
    
    private Path richiediFilePathValida(String filePath, 
                                        String tipoDato) throws PopolaDBException 
    {
        
        try {
            URL url = ClassLoader.getSystemResource(filePath);

            Objects.requireNonNull(url);
            
            URI uri = url.toURI();
            
            Objects.requireNonNull(uri);
            
            Path path = Paths.get(uri);
            
            Objects.requireNonNull(path);
            
            return path;
                    
        } catch(URISyntaxException | NullPointerException ex) {
            LOGGER.severe("STARTUP TASK: POPOLA DB: comuni e province: file path non valida. "
                                +"Input file path: '" + filePath + "' per dato '" + tipoDato+ "'. DETTAGLI: " + ex.getMessage());
            
            throw new PopolaDBException("File path per dato '" + tipoDato + "' non è valida. "
                                        +"Input file path: '" + filePath +"'. DETTAGLI: " +ex.getMessage());
        } 
        
    } 
    

    /**
     * Qui viene caricato il file delle province.
     */
    private void popolaDBProvince(Path pathProvince) throws PopolaDBException, IOException {

        try (Reader reader = Files.newBufferedReader(pathProvince)) {

            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .withIgnoreQuotations(true)
                    .build();

            try (CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withCSVParser(parser)
                    .build()) {

                String[] line;

                // salta l'header
                csvReader.readNext();
                
                // leggi ogni riga fino alla fine del csv
                while ((line = csvReader.readNext()) != null) {
                    
                    String sigla = line[0];
                    String nome = line[1];
                    String regione = line[2];
                    
                    String siglaAggiustata = sigla.trim();
                    String nomeAggiustato = nome.trim();
                    String regioneAggiustata = regione.trim();

                    // System.out.println(sigla + " " + provincia + " " + regione);
                    
                    // ***********************+
                    // EDGE CASES
                    // ***********************+

                    
                    // edge case: la sigla di Roma è "Roma", ma dovrebbe essere RM
                    if(sigla.trim().equals("Roma")) {
                       siglaAggiustata = "RM"; 
                    } 
                    
                    Provincia nuovaProvincia = new Provincia(
                            siglaAggiustata,
                            nomeAggiustato,
                            regioneAggiustata
                    );
                    
                    // salva la provincia
                    this.provinceRepository.save(nuovaProvincia);
                    
                }
            } catch (CsvValidationException e) {
                throw new PopolaDBException(e.getMessage());
            }
        }
    }


    /**
     * Qui viene caricato il file dei comuni.
     */
    private void popolaDBComuni(Path pathComuni) throws PopolaDBException, IOException {

        try (Reader reader = Files.newBufferedReader(pathComuni)) {

            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .withIgnoreQuotations(true)
                    .build();

            try (CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withCSVParser(parser)
                    .build()) {

                String[] line;

                // salta l'header
                csvReader.readNext();

                // leggi ogni riga fino alla fine del csv
                while ((line = csvReader.readNext()) != null) {

                    String nomeComune = line[2];
                    String nomeProvincia = line[3];

                    String nomeComuneAggiustato = nomeComune.trim();
                    String nomeProvinciaAggiustato = nomeProvincia.trim();

                    // cerca l'id della provincia che ha il nome provincia
                    // di questo comune
                    Optional<Provincia> forseProvincia = this.provinceRepository.trovaProvinciaPerNomeEsatto(nomeProvinciaAggiustato);
                    
                    // ***************
                    // EDGE CASE
                    // ***************
                    // la provincia del comune è "Verbano-Cusio-Ossola"
                    // in questo caso, trova invece la provincia "Verbania", che invece è quella 
                    // che deve essere correttamente associata a tutti i comuni che sono attualmente 
                    // associati con "Verbano-Cusio-Ossola".
                    
                    boolean provinciaEVerbanoCusioOssola = nomeProvinciaAggiustato.equals("Verbano-Cusio-Ossola");
                    
                    //  i controlli più specifici sulla non esistenza/non match 
                    //  di una provincia, vanno messi prima di potenzialmente
                    // lanciare l'errore generico sulla provincia
                    if(forseProvincia.isEmpty() && provinciaEVerbanoCusioOssola) {
                           
                        //  trova la provincia di Verbania
                        Optional<Provincia> forseProvinciaVerbania = this.provinceRepository.trovaProvinciaPerNomeEsatto("Verbania");
                        
                        // nemmeno la provincia di Verbania esiste
                        // questo dovrebbe essere raro
                        if(forseProvinciaVerbania.isEmpty()) {
                            throw new PopolaDBException("Durante il caricamento del comune '" 
                                                        + nomeComune + "', la cui provincia (nel csv) "
                                                        + "è '" + nomeProvinciaAggiustato + "', nemmeno la provincia 'Verbania' è stata trovata.");
                        }
                        
                        Provincia provinciaVerbaniaFromDB = forseProvinciaVerbania.get();

                        Comune nuovoComune = new Comune(provinciaVerbaniaFromDB, nomeComuneAggiustato);
                        // salva il comune
                        this.comuniRepository.save(nuovoComune);
                        
                    }
                    // provincia non esiste in DB: edge case non gestito
                    else if(forseProvincia.isEmpty()) {
                        throw new PopolaDBException("Durante il caricamento del comune '" + nomeComune + "' da csv, "
                                                    +"nella tabella delle province "
                                                    +" non è stata trovata una provincia il cui nome provincia corrisponde "
                                                    +"esattamente con '" + nomeProvincia + "'. Possibili motivi: non esiste questa provincia nella "
                                                    +"tabella delle province, o la provincia di questo comune non "
                                                    +"corrisponde esattamente a una provincia in DB.");
                    } 
                    // nessun errore
                    else {
                        // la managed entity dal DB
                        Provincia provinciaFromDB = forseProvincia.get();

                        Comune nuovoComune = new Comune(provinciaFromDB, nomeComuneAggiustato);
                        // salva il comune
                        this.comuniRepository.save(nuovoComune);              
                    }
                    

                }
            } catch (CsvValidationException e) {
                throw new PopolaDBException(e.getMessage());
            }
        }
    }


    /**
     * Devo caricare comuni e province?
     */
    private boolean devoPopolareDBComuniEProvince() {
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
    private void impostaVocePopolaDBComuniEProvinceComeCaricato() {
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
