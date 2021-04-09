package elections;

import elections.entities.*;
import elections.exceptions.CitizenAlreadyIsAMemberOfParty;
import elections.exceptions.CitizenIsNotExist;

import java.util.Arrays;

public class Management {

    private Citizen[] citizens = new Citizen[10]; // физический размер - выделено памяти
    private int citizenCount = 0; // логический размер - используется

    private BallotBox[] boxes = new BallotBox[10];
    private int boxCount = 0;

    private Party[] parties = new Party[10];
    private int partyCount = 0;


    public void init() {
    }

    public BallotBox getBallotBox(int num) {
        return boxes[num];
    }

    public Party[] showParties() {
        return Arrays.copyOf(parties, partyCount);
    }

    public Citizen[] showCitizens() {
        return Arrays.copyOf(citizens, citizenCount);
    }

    public BallotBox[] getBallotBoxes() {
        return Arrays.copyOf(boxes, boxCount);
    }

    public boolean addParty(Party party) {
        ensurePartyCapacity(partyCount+1);
        parties[partyCount++] = party;
        return true;
    }

    private void ensurePartyCapacity(int newCapacity) {
        if (newCapacity > parties.length) {
            parties = Arrays.copyOf(parties, parties.length * 2);
        }
    }

    public void addMemberToParty(Party party, String citizenPassport) throws CitizenIsNotExist, CitizenAlreadyIsAMemberOfParty {
        Citizen c = findCitizen(citizenPassport);
        if (c == null) {
            throw new CitizenIsNotExist(citizenPassport);
        }
        Party p = findParty(c);
        if (p != null) {
            throw new CitizenAlreadyIsAMemberOfParty(c, p);
        }
        party.addMember(c);
    }

    private Party findParty(Citizen c) {
        for (int i = 0; i < partyCount; i++) {
            if (parties[i].contains(c)) return parties[i];
        }
        return null;
    }

    private Citizen findCitizen(String citizenPassport) {
        for (int i = 0; i < citizenCount; i++) {
            if (citizens[i].getPassport().equals(citizenPassport)) return citizens[i];
        }
        return null;
    }


    public boolean addCitizen(Citizen citizen, BallotBox box) {
        ensureCitizenCapacity(citizenCount+1);
        citizens[citizenCount++] = citizen;
        citizen.setBallotBox(box);
        box.addCitizen(citizen);
        return true;
    }

    private void ensureCitizenCapacity(int newCapacity) {
        if (newCapacity > citizens.length) {
            citizens = Arrays.copyOf(citizens, citizens.length * 2);
        }
    }

    public boolean addBallotBox(BallotBox box) {
        ensureBallotBoxCapacity(boxCount+1);
        boxes[boxCount++] = box;
        return true;
    }

    private void ensureBallotBoxCapacity(int newCapacity) {
        if (newCapacity > boxes.length) {
            boxes = Arrays.copyOf(boxes, boxes.length * 2);
        }
    }

    public BallotBox[] findAppropriateBallotBoxes(Citizen citizen) {
        BallotBox[] result = new BallotBox[boxCount];
        int appropriated = 0;
        for (int i = 0; i < boxCount; i++) {
            if (boxes[i].check(citizen)) {
                result[appropriated++] = boxes[i];
            }
        }
        return Arrays.copyOf(result, appropriated);
    }

    public boolean exists(Citizen citizen) {
        for (int i = 0; i < citizenCount; i++) {
            if (citizen.equals(citizens[i])) return true;
        }
        return false;
    }

    public BallotBox findBallotBox(int num) {
        for (BallotBox box : boxes) {
            if (box.getNumber() == num) return box;
        }
        return null;
    }
}
