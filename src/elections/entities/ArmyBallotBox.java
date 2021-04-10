package elections.entities;

import elections.Management;

public class ArmyBallotBox extends BallotBox {

    public ArmyBallotBox(String address, Management management) {
        super(address, management);
    }

    public boolean check(Citizen citizen) {
        int year = getManagement().getElectionYear();
        return !citizen.isCarantine() && (year - citizen.getBirthYear()>=18 && year - citizen.getBirthYear() <= 21);
    }

    @Override
    public String toString() {
        return super.toString() + " - army";
    }
}
