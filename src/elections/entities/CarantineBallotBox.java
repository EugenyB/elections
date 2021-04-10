package elections.entities;

import elections.Management;

public class CarantineBallotBox extends BallotBox {

    public CarantineBallotBox(String address, Management management) {
        super(address, management);
    }

    @Override
    public boolean check(Citizen citizen) {
        int year = getManagement().getElectionYear();
        return citizen.isCarantine() && (year - citizen.getBirthYear() >= 18);
    }

    @Override
    public String toString() {
        return super.toString() + " - carantine";
    }
}
