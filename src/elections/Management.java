package elections;

import elections.entities.BallotBox;
import elections.entities.Citizen;

import java.util.Arrays;

public class Management {

    private Citizen[] citizens = new Citizen[10]; // физический размер - выделено памяти
    private int citizenCount = 0; // логический размер - используется

    private BallotBox[] boxes = new BallotBox[10];
    private int boxCount = 0;


    public void showParties() {

    }

    public void showCitizens() {

    }

    public void showBallotBoxes() {

    }

    public void addParty() {

    }

    public boolean addCitizen(Citizen citizen) {
        ensureCitizenCapacity(citizenCount+1);
        citizens[citizenCount++] = citizen;
        return true;
    }

    private void ensureCitizenCapacity(int newCapacity) {
        if (newCapacity > citizens.length) {
            citizens = Arrays.copyOf(citizens, citizens.length * 2);
        }
    }

    public void addBallotBox() {

    }


}
