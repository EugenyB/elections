package elections.exceptions;

import elections.entities.Citizen;
import elections.entities.Party;

public class CitizenAlreadyIsAMemberOfParty extends Exception {
    public CitizenAlreadyIsAMemberOfParty(Citizen citizen, Party party) {
        super(String.format("Citizen with passport %s already is a member of a party %s", citizen.getPassport(), party.getName()));
    }
}
