package elections.exceptions;

public class CitizenIsNotExist extends Exception {
    public CitizenIsNotExist(String citizenPassport) {
        super("Citizen with this passport is not exists : " + citizenPassport);
    }
}
