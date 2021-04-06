package elections.entities;

import java.time.LocalDate;

public class NormalBallotBox extends BallotBox {

    public NormalBallotBox(String address) {
        super(address);
    }

    @Override
    public boolean check(Citizen citizen) {
        int year = LocalDate.now().getYear();
        return !citizen.isCarantine() && (year - citizen.getBirthYear() > 21);
    }

    @Override
    public String toString() {
        return super.toString() + " - normal";
    }
}
