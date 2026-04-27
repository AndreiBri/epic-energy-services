package andrei.epic_energy_services.exceptions;

public class FileUploadException extends RuntimeException {
    public FileUploadException(String message) {
        super("Error during file upload. DETAILS: " + message);
    }
}
