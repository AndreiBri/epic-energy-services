package andrei.epic_energy_services.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID itemId) {
        super("The item with ID '" + itemId + "' has not been found.");
    }
    

    public NotFoundException(UUID itemId, String informalEntity) {
        super("The item '" + informalEntity + "' with ID " + itemId + " has not been found.");
    }

    public NotFoundException(String msg) {
        super(msg);
    }

}