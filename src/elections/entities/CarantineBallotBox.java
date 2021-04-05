package elections.entities;

import java.time.LocalDate;

public class CarantineBallotBox extends BallotBox {
    @Override
    public boolean check(Citizen citizen) {
        int year = LocalDate.now().getYear();
        return citizen.isCarantine() && (year - citizen.getBirthYear() >= 18);
    }
}
