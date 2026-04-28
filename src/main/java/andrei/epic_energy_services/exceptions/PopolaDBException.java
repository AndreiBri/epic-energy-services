package andrei.epic_energy_services.exceptions;

public class PopolaDBException extends RuntimeException {
    public PopolaDBException(String message) {
        super("Errore durante procedura di popolamento del DB. DETTAGLI: " + message);
    }
}
