package andrei.epic_energy_services.exceptions;

public class InvalidFileUploadedException extends RuntimeException {
    public InvalidFileUploadedException(String message) {
        super("File sent is not valid. DETAILS: " + message);
    }
}
