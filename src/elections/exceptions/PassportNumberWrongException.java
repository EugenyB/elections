package elections.exceptions;

public class PassportNumberWrongException extends Exception {
    public PassportNumberWrongException(String passport) {
        super("Wrong passport number :" + passport);
    }
}
