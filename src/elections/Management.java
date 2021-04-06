package elections;

import elections.entities.*;

import java.util.Arrays;

public class Management {

    private Citizen[] citizens = new Citizen[10]; // физический размер - выделено памяти
    private int citizenCount = 0; // логический размер - используется

    private BallotBox[] boxes = new BallotBox[10];
    private int boxCount = 0;


    public void init() {
        boxes[0] = new NormalBallotBox("First Street, 1");
        boxes[1] = new NormalBallotBox("First Street, 20");
        boxes[2] = new ArmyBallotBox("Second Str, 2");
        boxes[3] = new CarantineBallotBox("Another Str, 13");
        boxCount = 4;
        try {
            Citizen c = Citizen.of("Ian Iza", "123456789", 1995);
            addCitizen(c, boxes[0]);
            c = Citizen.of("Bet Bets", "111111111", 1990);
            addCitizen(c, boxes[0]);

            c = Citizen.of("Ben Yan", "987654321", 2000);
            addCitizen(c, boxes[2]);

            c = Citizen.of("Bin Bolnoy", "123123123", 1998);
            c.setCarantine(true);
            addCitizen(c, boxes[3]);

            c = Citizen.of("Ben Best", "212121212", 1990);
            addCitizen(c, boxes[1]);

        } catch (Exception e) {}
    }

    public void showParties() {

    }

    public void showCitizens() {

    }

    public BallotBox[] getBallotBoxes() {
        return Arrays.copyOf(boxes, boxCount);
    }

    public void addParty() {

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

    public void addBallotBox() {

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
