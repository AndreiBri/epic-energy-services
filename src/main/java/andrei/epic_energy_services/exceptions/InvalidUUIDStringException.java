package andrei.epic_energy_services.exceptions;

public class InvalidUUIDStringException extends InvalidDataFormatException {
    public InvalidUUIDStringException(String itemId) {
        super("The string '" + itemId + "' cannot be cast to a valid UUID.");
    }
    
}
