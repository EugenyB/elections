package elections.entities;

import elections.Management;

public class NormalBallotBox extends BallotBox {

    public NormalBallotBox(String address, Management management) {
        super(address, management);
    }

    @Override
    public boolean check(Citizen citizen) {
        int year = getManagement().getElectionYear();
        return !citizen.isCarantine() && (year - citizen.getBirthYear() > 21);
    }

    @Override
    public String toString() {
        return super.toString() + " - normal";
    }
}
