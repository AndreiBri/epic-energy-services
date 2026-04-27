package andrei.epic_energy_services.exceptions;

import java.util.ArrayList;
import java.util.List;

public class PayloadValidationException extends RuntimeException {
    private List<String> errors = new ArrayList<>();

    public PayloadValidationException(String message) {
        super(message);
    }

    public PayloadValidationException(List<String> errors) {
        super("Errore di validazione del payload: Almeno un campo non è valido.");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}