package elections.entities;

import java.time.LocalDate;

public class BallotBox {

    private Citizen[] citizens = new Citizen[10];
    private int count = 0;


    public boolean check(Citizen citizen) {
        int year = LocalDate.now().getYear();
        return !citizen.isCarantine() && (year - citizen.getBirthYear() > 21);
    }
}
