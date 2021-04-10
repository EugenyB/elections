package elections;

import elections.entities.BallotBox;
import elections.entities.Citizen;
import elections.entities.Party;
import elections.exceptions.CitizenAlreadyIsAMemberOfPartyException;
import elections.exceptions.CitizenIsNotExistException;

import java.util.Arrays;

public class Management {

    private Citizen[] citizens = new Citizen[10]; // физический размер - выделено памяти
    private int citizenCount = 0; // логический размер - используется

    private BallotBox[] boxes = new BallotBox[10];
    private int boxCount = 0;

    private Party[] parties = new Party[10];
    private int partyCount = 0;

    private int electionMonth;
    private int electionYear;

    private int[][] votes;

    public void init() {
    }

    public int getElectionMonth() {
        return electionMonth;
    }

    public int getElectionYear() {
        return electionYear;
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

    public BallotBox[] showBallotBoxes() {
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

    public void addMemberToParty(Party party, String citizenPassport) throws CitizenIsNotExistException, CitizenAlreadyIsAMemberOfPartyException {

        Citizen c = findCitizen(citizenPassport);
        if (c == null) {
            throw new CitizenIsNotExistException(citizenPassport);
        }
        Party p = findParty(c);
        if (p != null) {
            throw new CitizenAlreadyIsAMemberOfPartyException(c, p);
        }
        party.addMember(c);
    }

    public Party findParty(Citizen c) {
        for (int i = 0; i < partyCount; i++) {
            if (parties[i].contains(c)) return parties[i];
        }
        return null;
    }

    public Citizen findCitizen(String citizenPassport) {
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

    public boolean checkIfCitizenWantToVote(Citizen citizen){
        if (citizen.checkIfCitizenWantToVote(citizen) ){
            return true;
        }else return false;
    }


    public void chooseParty (Citizen citizen ){

    }

    public String partyGetName(Party party){
        return party.getName();
    }


    public void setElectionDate(int month, int year) {
        electionMonth = month;
        electionYear = year;
    }

    public void acceptVoting(Citizen citizen, Party party) {
        BallotBox ballotBox = citizen.getBallotBox();

        int i = indexOfBallotBox(ballotBox);

        int j = indexOfParty(party);

        votes[i][j]++;
    }

    private int indexOfParty(Party party) {
        int j = 0;
        while (!party.equals(parties[j])) j++;
        return j;
    }

    private int indexOfBallotBox(BallotBox ballotBox) {
        int i = 0;
        while (!ballotBox.equals(boxes[i])) i++;
        return i;
    }

    public void clearVotes() {
        votes = new int[boxCount][partyCount];
    }

    public int getVotes(int p, int b) {
        return votes[b][p];
    }

    public int getResultByParty(int p) {
        int sum = 0;
        for (int i = 0; i < votes.length; i++) {
            sum += votes[i][p];
        }
        return sum;
    }
}
