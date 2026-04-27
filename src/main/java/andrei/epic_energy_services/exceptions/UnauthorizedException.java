package andrei.epic_energy_services.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super("You are not authenticated or not authorized to access this resource: DETAILS: " + message);
    }
}