package elections.exceptions;

public class CitizenIsNotExistException extends Exception {
    public CitizenIsNotExistException(String citizenPassport) {
        super("Citizen with this passport is not exists : " + citizenPassport);
    }
}
