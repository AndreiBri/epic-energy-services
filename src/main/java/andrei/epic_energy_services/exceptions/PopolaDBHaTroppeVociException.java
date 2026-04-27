package andrei.epic_energy_services.exceptions;

public class PopolaDBHaTroppeVociException extends RuntimeException {
    public PopolaDBHaTroppeVociException(String message) {
        super("La tabella che tiene traccia del popolamento del DB non ha un numero " 
                +"di voci valido. DETTAGLI: " + message);
    }
}
